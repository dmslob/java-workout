package com.dmslob.collection.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void frequency() {
        String[] numbers = {"12", "23", "34", "12", "23"};
        //Map<String, Long> freqs = Arrays.stream(numbers).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //System.out.println(freqs);

        for (String number : numbers) {
            int frequency = Collections.frequency(Arrays.asList(numbers), number);
            System.out.println(number + " : " + frequency);
        }
    }

    public static void main(String[] args) {
        frequency();
        List<Employee> employees = listEmployees();

        // filter all the employee whose age is greater than 20
        List<String> employeeFilteredList = employees.stream()
                .filter(e -> e.getAge() > 20)
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(employeeFilteredList.toString());

        // count number of employees with age 25
        long count = employees.stream()
                .filter(e -> e.getAge() > 25)
                .count();

        System.out.println("Number of employees with age 25 are : " + count);

        // find the employee with name Mary.
        Optional<Employee> e1 = employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase("Mary")).findAny();
        e1.ifPresent(employee -> System.out.println(employee.getName()));

        // find maximum age of employee
        OptionalInt max = employees.stream().
                mapToInt(Employee::getAge).max();
        max.ifPresent(value -> System.out.println("Maximum age of Employee: " + max.getAsInt()));

        // sort all the employee on the basis of age
        employees.sort(Comparator.comparingInt(Employee::getAge));
        System.out.println();
        for (Employee employee : employees) {
            System.out.println(employee.getAge());
        }

        // Join the all employee names with ','
        List<String> employeeNames = employees
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        String employeeNamesStr = String.join(", ", employeeNames);
        System.out.println("Employees are: " + employeeNamesStr);

        // group them by employee name
        Map<String, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getName));
        map.forEach((name, employeeListTemp) -> System.out.println(name + " => " + employeeListTemp.size()));

        Map<String, Long> wosNumberCountMap = employees
                .stream().collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));

        removeDuplicates();

        mapFunc();

        forEachOrdered();
    }

    static void removeDuplicates() {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 3, 2, 4, 2};
        List<Integer> listWithDuplicates = Arrays.asList(arr);

        Set<Integer> setWithoutDups = listWithDuplicates.stream().collect(Collectors.toSet());
        setWithoutDups.forEach((i) -> System.out.print(" " + i));

        List<Integer> listWithoutDups = listWithDuplicates.stream().distinct().collect(Collectors.toList());
        listWithoutDups.forEach((i) -> System.out.print(" " + i));
    }

    static void mapFunc() {
        Integer[] arr = new Integer[]{100, 24, 13, 44, 114, 200, 40, 112};

        List<Integer> list = Arrays.asList(arr);
        OptionalDouble average = list.stream()
                .mapToInt(n -> n * n)
                .filter(n -> n > 10000)
                .average();

        System.out.println();
        average.ifPresent(value -> System.out.println(average.getAsDouble()));
    }

    static List<Employee> listEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 23));
        employees.add(new Employee("John", 27));
        employees.add(new Employee("Bobby", 19));
        employees.add(new Employee("Jim", 28));
        employees.add(new Employee("Brus", 27));
        employees.add(new Employee("Travis", 18));
        employees.add(new Employee("Gary", 26));
        employees.add(new Employee("Stephan", 32));
        employees.add(new Employee("Mary", 31));
        employees.add(new Employee("Martin", 19));

        return employees;
    }

    static void forEachOrdered() {
        List<Integer> list = Arrays.asList(10, 19, 20, 1, 2);
        list.stream().forEachOrdered(System.out::println);

        Stream<String> stream = Stream.of("GFG", "Geeks", "for", "GeeksforGeeks");

        stream.flatMap(str -> Stream.of(str.charAt(2))).forEachOrdered(System.out::println);
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
