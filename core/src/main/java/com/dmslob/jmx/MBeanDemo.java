package com.dmslob.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class MBeanDemo {

    public static void main(String[] args) {
        MBeanDemo mBeanDemo = new MBeanDemo();
        Calculator calculator = new Calculator();

        try {
            mBeanDemo.registerWithJmxAgent(calculator);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        mBeanDemo.startConsoleApp(calculator);
    }

    void startConsoleApp(Calculator calculator) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------");
            String firstArg = getUserInput(scanner, "enter first number");
            double d1 = toDouble(firstArg);

            String secondArg = getUserInput(scanner, "enter second number");
            double d2 = toDouble(secondArg);

            double sum = calculator.add(d1, d2);
            System.out.printf("sum = %s (rounded to %s decimal places)%n",
                    sum, calculator.getDecimalPlaces());
        }
    }

    double toDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number, defaulting to 0");
            return 0;
        }
    }

    void registerWithJmxAgent(Calculator calculator) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.logicbig.example:type=calculator");
        mbs.registerMBean(calculator, name);
    }

    public String getUserInput(Scanner scanner, String msg) {
        System.out.print(msg + ">");
        String s = scanner.nextLine();
        if ("exit".equals(s)) {
            System.exit(0);
        }
        return s;
    }
}
