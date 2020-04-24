package com.dmslob.stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    private static final Logger LOGGER = LogManager.getLogger(StreamTest.class);

    @Test
    public void maxTest() {
        /**
         * Just warning: Method reference mapped to Comparator interface does not fulfill
         * the Comparator contract
         */
        Integer actualResult = Stream.of(-1, 0, 1).max(Math::max).get();
        LOGGER.info("actualResult: {}", actualResult);

        Assert.assertEquals(new Integer(-1), actualResult);
    }

    @Test
    public void debugStreamTest() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> LOGGER.info("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> LOGGER.info("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    @Test
    public void streamWithConsumerTest() {
        Stream.of(1, 2)
                .peek(
                        ((Consumer<Integer>) (i1) -> {
                            i1 = i1 + 1;
                        }).andThen((i2) -> {
                            i2 = i2 + 2;
                        }))
                .forEach(LOGGER::info);
    }

    @Test
    public void mutateTheStateTest() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(10, 20));
        points.add(new Point(5, 10));

        List<Point> newPoints = points.stream()
                .peek(point -> point.setX(11))
                .collect(Collectors.toList());

        LOGGER.info("{}", newPoints.toString());
    }

    @Test
    public void sortedStreamTest() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(10, 20));
        points.add(new Point(5, 10));
        points.add(new Point(1, 100));
        points.add(new Point(50, 2000));

        // displaying the stream with elements
        // sorted according to x coordinate
        Comparator<Integer> pointComparator = Integer::compareTo;
        points.stream()
                //.sorted((p1, p2) -> p1.getX().compareTo(p2.getX()))
                .sorted(Comparator.comparing(p -> p.getX(), pointComparator))
                .forEach(LOGGER::info);
    }

    @Test
    public void findItemsFromOneListOnValuesFromAnotherListTest() {
        Integer expectedId = 1;
        List<Employee> employeeList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();

        populate(employeeList, departmentList);

        List<Employee> filteredList = employeeList.stream()
                .filter(employee -> departmentList.stream()
                        .anyMatch(department -> department.getDepartment().equals("SALES") &&
                                employee.getEmployeeId().equals(department.getEmployeeId())
                        )
                )
                .collect(Collectors.toList());

        Assert.assertEquals(1, filteredList.size());
    }

    private void populate(List<Employee> employeeList, List<Department> departmentList) {
        Employee bobby = new Employee(1, "Bobby");
        Employee molly = new Employee(2, "Molly");
        Employee freddy = new Employee(3, "Freddy");
        employeeList.addAll(Arrays.asList(bobby, molly, freddy));

        Department salesDepartment = new Department(1, "SALES");
        Department it = new Department(2, "IT");
        Department newDepartment = new Department(3, "NEW");
        departmentList.addAll(Arrays.asList(salesDepartment, it, newDepartment));
    }
}