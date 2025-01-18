package com.dmslob.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
public class StreamTest {

    @Test
    public void should_have_warning_when_no_comparator_contract_in_method_reference() {
        /*
         * Warning:
         * Method reference mapped to Comparator interface does not fulfill
         * the Comparator contract
         * use .max(Comparator.naturalOrder())
         */
        Integer maxNumber = Stream.of(-1, 0, 1)
                .max(Math::max).get();

        Assert.assertEquals(Integer.valueOf(-1), maxNumber);
    }

    @Test
    public void should_dropWhile() {
        // given
        var nums = List.of(2, 4, 6, 3, 1, 8, 7);
        var expectedNums = List.of(3, 1, 8, 7);
        // when
        var result = nums.stream()
                .dropWhile(n -> n % 2 == 0)
                .toList();
        // then
        assertThat(result).isEqualTo(expectedNums);
    }

    @Test
    public void should_takeWhile() {
        // given
        var numbers = List.of(25, 26, 28, 31, 29, 27, 22);
        var expectedNums = List.of(25, 26, 28);
        // when
        var result = numbers.stream()
                .takeWhile(t -> t <= 30)
                .toList();
        // then
        assertThat(result).isEqualTo(expectedNums);
    }

    @Test
    public void should_sort_stream_with_comparator() {
        // given
        var points = new ArrayList<Point>();
        points.add(new Point(10, 20));
        points.add(new Point(5, 10));
        points.add(new Point(1, 100));
        points.add(new Point(50, 2000));

        Comparator<Integer> pointComparator = Integer::compareTo;

        // when
        var sortedPoints = points.stream()
                //.sorted((p1, p2) -> p1.getX().compareTo(p2.getX()))
                .sorted(Comparator.comparing(Point::getX, pointComparator))
                .collect(toList());
        // then
        assertThat(sortedPoints).isNotSameAs(points);
        assertThat(sortedPoints.get(0).getX())
                .isEqualTo(1);
    }

    @Test
    public void should_throw_exception_when_interference_occurs() {
        var strings = new ArrayList<>(Arrays.asList("one", "two"));
        // This will fail as the peek operation will attempt to add the
        // string "three" to the source after the terminal operation has
        // commenced.
        assertThatThrownBy(() -> strings
                .stream()
                // Don't do this! Interference occurs here.
                .peek(s -> strings.add("three"))
                .collect(Collectors.joining(" ")))
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void should_copy_source() {
        // given
        var users = new ArrayList<>(Arrays.asList("Bob", "Arnold"));
        var usersStream = users.stream();
        users.add("Anna");

        // when
        String joinedUserNames = usersStream
                .collect(Collectors.joining(" "));
        // then
        assertThat(joinedUserNames).isEqualTo("Bob Arnold Anna");
    }

    @Test
    public void should_find_hashtags() {
        // given
        var lines = List.of(
                "#Java and #Scala are the languages of cognitive and AI dev.",
                "#Java and #Kotlin are the languages of cognitive and AI dev.",
                "#Scala and #Python are the languages of cognitive and AI dev.",
                "#Go and #Java are the languages of cognitive and AI dev."
        );
        LinkedHashMap<String, Long> expected = new LinkedHashMap<>();
        expected.put("#Java", 3L);
        expected.put("#Scala", 2L);
        expected.put("#Kotlin", 1L);
        expected.put("#Python", 1L);
        expected.put("#Go", 1L);
        // when
        LinkedHashMap<String, Long> tagPerFrequency = lines.stream()
                .flatMap(lineWithTweets -> Arrays.stream(lineWithTweets.split(" ")))
                .filter(tweet -> tweet.matches("#\\w+")) // or "#[A-Za-z0-9_]+"
                .collect(Collectors.groupingBy(String::toString, LinkedHashMap::new, Collectors.counting()));
        // then
        assertThat(tagPerFrequency).isEqualTo(expected);
    }

    @Test
    public void should_sum_numbers() {
        // given
        var integers = Arrays.asList(1, 2, 3, 4, 5);
        var expected = 15;
        // when
        //var sum = integers.stream().collect(Collectors.summingInt(Integer::intValue));
        var sum = integers.stream().mapToInt(Integer::intValue).sum();
        // then
        assertThat(sum).isEqualTo(expected);
//        Map<String, Long> labelsByGroups = allLabels.stream()
//                .collect(Collectors.groupingBy(Label::getName, Collectors.counting()));
    }

    record Dev(Long id, String name) {
    }

    record Task(Long id, String title) {
    }

    record Assignment(Long devId, Long taskId) {
    }

    @Test
    public void should_find_and_group_assignments() {
        // given
        // devs
        var devSergio = new Dev(1L, "Sergio Dev");
        var devDmytro = new Dev(2L, "Dmytro Dev");
        // tasks
        var task_1 = new Task(1L, "Task 1");
        var task_2 = new Task(2L, "Task 2");
        var task_3 = new Task(3L, "Task 3");
        var task_4 = new Task(4L, "Task 4");
        // assignments
        var assignment_1 = new Assignment(1L, 1L);
        var assignment_2 = new Assignment(1L, 2L);
        var assignment_3 = new Assignment(2L, 3L);
        var assignment_4 = new Assignment(2L, 4L);
        var devs = List.of(devSergio, devDmytro);
        var tasks = List.of(task_1, task_2, task_3, task_4);
        var assignments = List.of(assignment_1, assignment_2, assignment_3, assignment_4);
        var expectedResult = Map.of(
                devSergio.name, List.of(task_1.title, task_2.title),
                devDmytro.name, List.of(task_3.title, task_4.title)
        );
        // when
        var actualResult = groupTasksByDev(devs, tasks, assignments);
        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    // Map<Dev::name, List.of(Task::title)>
    private Map<String, List<String>> groupTasksByDev(
            List<Dev> devs,
            List<Task> tasks,
            List<Assignment> assignments) {
        return devs.stream()
                .collect(Collectors.toMap(Dev::name, dev ->
                        assignments.stream()
                                .filter(assignment -> assignment.devId.equals(dev.id))
                                .map(Assignment::taskId)
                                .flatMap(taskId -> tasks.stream()
                                        .filter(task -> taskId.equals(task.id))
                                        .map(Task::title))
                                .toList()
                ));
    }

    private Map<String, List<String>> groupTasksByDev2(
            List<Dev> devs,
            List<Task> tasks,
            List<Assignment> assignments) {
        return devs.stream()
                .collect(Collectors.groupingBy(Dev::name,
                        Collectors.flatMapping(dev -> assignments.stream()
                                        .filter(assignment -> assignment.devId.equals(dev.id))
                                        .map(Assignment::taskId)
                                        .flatMap(taskId -> tasks.stream()
                                                .filter(task -> taskId.equals(task.id))
                                                .map(Task::title)),
                                toList())
                ));
    }
}