package com.dmslob.tasks.ratelimiter;

import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccessLogAnalyzerTest {

    record AccessLog(Instant timestamp, String endpoint) {
    }

    record Violation(
            String endpoint,
            Instant windowStart,
            Instant windowEnd,
            int requestCount) {
    }

    static class AccessLogAnalyzer {
        public AccessLogAnalyzer() {
        }

        public List<Violation> findViolations(
                List<AccessLog> events,
                Duration window,
                int maxRequests) {
            Objects.requireNonNull(events);
            Objects.requireNonNull(window);

            if (window.isZero() || window.isNegative()) {
                throw new IllegalArgumentException("Window must be positive");
            }
            if (maxRequests < 1) {
                throw new IllegalArgumentException("maxRequests must be positive");
            }

            Map<String, Deque<Instant>> requestsByEndpoint = new HashMap<>();
            List<Violation> violations = new ArrayList<>();

            for (AccessLog event : events) {
                Deque<Instant> timestamps = requestsByEndpoint.computeIfAbsent(
                        event.endpoint(),
                        ignored -> new ArrayDeque<>()
                );

                Instant windowStart = event.timestamp().minus(window);

                while (!timestamps.isEmpty()
                        && !timestamps.peekFirst().isAfter(windowStart)) {
                    timestamps.pollFirst();
                }

                timestamps.addLast(event.timestamp());

                if (timestamps.size() > maxRequests) {
                    violations.add(new Violation(
                            event.endpoint(),
                            timestamps.peekFirst(),
                            event.timestamp(),
                            timestamps.size()
                    ));
                }
            }
            return violations;
        }
    }

    private static final Instant BASE_TIME = Instant.parse("2026-08-21T10:00:00Z");
    private static final Duration WINDOW = Duration.ofMinutes(1);

    private AccessLog event(long seconds, String endpoint) {
        return new AccessLog(
                BASE_TIME.plusSeconds(seconds),
                endpoint
        );
    }

    @Test
    void should_return_no_violations_when_requests_stay_within_limit() {
        // given
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(10, "/users"),
                event(20, "/users"),
                event(30, "/users"),
                event(40, "/users")
        );
        // when
        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 5);
        // then
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldDetectWhenEndpointExceedsRequestLimit() {
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(10, "/users"),
                event(20, "/users"),
                event(30, "/users"),
                event(40, "/users"),
                event(50, "/users")
        );

        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 5);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.endpoint()).isEqualTo("/users");
                    assertThat(violation.requestCount()).isEqualTo(6);
                    assertThat(violation.windowStart())
                            .isEqualTo(BASE_TIME);
                    assertThat(violation.windowEnd())
                            .isEqualTo(BASE_TIME.plusSeconds(50));
                });
    }

    @Test
    void shouldTreatDifferentEndpointsIndependently() {
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(10, "/users"),
                event(20, "/users"),

                event(30, "/orders"),
                event(40, "/orders"),
                event(50, "/orders")
        );

        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 3);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldDetectViolationOnlyForEndpointThatExceedsLimit() {
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(10, "/users"),
                event(20, "/users"),
                event(30, "/users"),

                event(0, "/orders"),
                event(10, "/orders")
        );

        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 3);

        assertThat(violations)
                .extracting(Violation::endpoint)
                .containsExactly("/users");
    }

    @Test
    void shouldRemoveRequestsOutsideSlidingWindow() {
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(10, "/users"),
                event(20, "/users"),
                event(61, "/users")
        );

        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 3);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldRespectWindowBoundary() {
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(60, "/users")
        );

        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 1);

        // The interval is (t - window, t].
        // Therefore the request exactly 60 seconds old is excluded.
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldCountRequestsInsideSlidingWindow() {
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(10, "/users"),
                event(20, "/users"),
                event(61, "/users"),
                event(62, "/users"),
                event(63, "/users")
        );

        List<Violation> violations = new AccessLogAnalyzer().findViolations(events, WINDOW, 3);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.endpoint()).isEqualTo("/users");
                    assertThat(violation.requestCount()).isEqualTo(4);
                });
    }

    @Test
    void shouldHandleMultipleViolations() {
        List<AccessLog> events = List.of(
                event(0, "/users"),
                event(1, "/users"),
                event(2, "/users"),

                event(10, "/orders"),
                event(11, "/orders"),
                event(12, "/orders")
        );

        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 2);

        assertThat(violations)
                .extracting(Violation::endpoint)
                .containsExactlyInAnyOrder(
                        "/users",
                        "/orders"
                );
    }

    @Test
    void shouldHandleEmptyInput() {
        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(
                        List.of(),
                        WINDOW,
                        10
                );

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldHandleSingleRequest() {
        List<AccessLog> events = List.of(
                event(0, "/users")
        );

        List<Violation> violations = new AccessLogAnalyzer()
                .findViolations(events, WINDOW, 1);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldRejectNullEvents() {
        assertThatThrownBy(() -> new AccessLogAnalyzer().findViolations(
                null,
                WINDOW,
                10
        ))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldRejectNullWindow() {
        assertThatThrownBy(() -> new AccessLogAnalyzer().findViolations(
                List.of(),
                null,
                10
        ))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldRejectZeroWindow() {
        assertThatThrownBy(() -> new AccessLogAnalyzer().findViolations(
                List.of(),
                Duration.ZERO,
                10
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Window must be positive");
    }

    @Test
    void shouldRejectNegativeWindow() {
        assertThatThrownBy(() -> new AccessLogAnalyzer().findViolations(
                List.of(),
                Duration.ofSeconds(-1),
                10
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Window must be positive");
    }

    @Test
    void shouldRejectZeroMaxRequests() {
        assertThatThrownBy(() -> new AccessLogAnalyzer().findViolations(
                List.of(),
                WINDOW,
                0
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("maxRequests must be positive");
    }

    @Test
    void shouldRejectNegativeMaxRequests() {
        assertThatThrownBy(() -> new AccessLogAnalyzer().findViolations(
                List.of(),
                WINDOW,
                -1
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("maxRequests must be positive");
    }
}
