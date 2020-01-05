package com.dmslob.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RedirectExample {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("manifest.mf"));
        PrintStream out = new PrintStream(
                new BufferedOutputStream(new FileOutputStream("test.out")));

        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = bufReader.readLine()) != null) {
            System.out.println(str);
        }
        out.close();

        System.setOut(console);
    }
}
