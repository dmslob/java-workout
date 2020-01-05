package com.dmslob.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class SystemInOutExample {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bistream = new BufferedReader(is);

        try {
            char c;
            int number;

            // Input
            System.out.println("Input name:");
            String nameStr = bistream.readLine();

            System.out.println("Input number:");
            String numberStr = bistream.readLine();
            number = Integer.valueOf(numberStr).intValue();
            System.out.println("Number: " + number);

            System.out.println("Char: ");
            c = (char) bistream.read();
            System.out.println("Char: " + c);

            // Output 
            PrintStream ps = new PrintStream(new FileOutputStream("res.txt"));
            ps.println("Result: " + nameStr + c + number);
            ps.close();
        } catch (IOException e) {
            System.out.println("Error IO: " + e);
        }
    }
}
