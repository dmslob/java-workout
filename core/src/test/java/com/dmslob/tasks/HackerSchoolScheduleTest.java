package com.dmslob.tasks;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HackerSchoolScheduleTest {
    /**
     * A student at HackerSchool is provided with a schedule of n days,
     * where each day can have up to m hours of lecture classes.
     * The schedule is represented by a binary matrix schedule,
     * where schedule[i][j] = 1 means there is a lecture at the hour of the day,
     * and schedule[i][j] = 0 means there is no lecture at that time.
     * if the student attends the first lecture at the x-th hour
     * and the last lecture at the y-th hour on a single day, then they spend (y - x + 1) hours at school that day.
     * The student is allowed to skip up to k lectures in total over all n days.
     * Determine the minimum total time (in hours) the student needs to attend school over all n days,
     * given that they can skip lectures optimally.
     * Example:
     * Consider n = 1, m = 5, schedule[][] = ["10001"] and k = 1.
     * The student can skip the last lecture of the first day, that is, schedule[0][4].
     * Then, they only have to attend one lecture at the o-th hour,
     * so the total time spent at school = 1, which is the minimum possible. Thus, the answer is 1.
     *
     * @param n        number of days
     * @param m        max hours per day
     * @param k        max total lectures allowed to skip
     * @param schedule binary matrix represented as an array of strings
     * @return minimum total hours spent at school
     */
    public static int minimizeTime(int n, int m, int k, String[] schedule) {
        // dp[j] stores the minimum time spent given we have skipped exactly j lectures
        // Initialize with a large value to represent "infinity", divided by 2 to prevent overflow
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            // Find all indices of lectures for the current day
            List<Integer> pos = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (schedule[i].charAt(j) == '1') {
                    pos.add(j);
                }
            }
            int c = pos.size();
            // cost[s] will store the minimum time spent on THIS day if we skip 's' lectures
            int[] cost = new int[c + 1];
            Arrays.fill(cost, Integer.MAX_VALUE / 2);
            cost[c] = 0; // Skipping all lectures on this day results in 0 hours spent
            // Calculate minimum time for skipping 's' lectures (where s < c)
            for (int s = 0; s < c; s++) {
                int attended = c - s;
                int minTime = Integer.MAX_VALUE;
                // We must attend a contiguous block of 'attended' lectures
                for (int start = 0; start <= s; start++) {
                    int end = start + attended - 1;
                    int time = pos.get(end) - pos.get(start) + 1;
                    minTime = Math.min(minTime, time);
                }
                cost[s] = minTime;
            }
            // Create the DP state for the next day
            int[] nextDp = new int[k + 1];
            Arrays.fill(nextDp, Integer.MAX_VALUE / 2);
            // Transition: Combine previous days' DP with today's cost calculations
            for (int j = 0; j <= k; j++) {
                if (dp[j] >= Integer.MAX_VALUE / 2) continue; // Skip unreachable states
                for (int s = 0; s <= c; s++) {
                    if (j + s <= k) {
                        nextDp[j + s] = Math.min(nextDp[j + s], dp[j] + cost[s]);
                    }
                }
            }
            // Move to the newly computed DP state
            dp = nextDp;
        }
        // The answer is the minimum time spent across any number of skipped lectures up to k
        int minTotalTime = Integer.MAX_VALUE;
        for (int j = 0; j <= k; j++) {
            minTotalTime = Math.min(minTotalTime, dp[j]);
        }
        return minTotalTime;
    }

    @Test
    public void should_find_minimum_time_for_schedule() {
        // given
        int n = 1;
        int m = 5;
        int k = 1;
        String[] schedule = {"10001"};
        // when
        int result = minimizeTime(n, m, k, schedule);
        // then
        assertThat(result).isEqualTo(1);
    }
}
