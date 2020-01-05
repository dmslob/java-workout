package com.dmslob.io;

import java.io.BufferedReader;
import java.io.*;

public class ReaderWriterExample {

    public static void main(String[] args) throws IOException {
        String f = "test.out";

        BufferedReader in = new BufferedReader(new StringReader("Build.xml"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));

        String s;
        while ((s = in.readLine()) != null) {
            out.print(s);
        }
        System.out.println(new BufferedReader(new StringReader("test.out")));
    }
}
