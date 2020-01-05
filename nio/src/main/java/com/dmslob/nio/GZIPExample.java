package com.dmslob.nio;

import java.io.*;
import java.util.zip.*;

public class GZIPExample {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("Build.xml"));
        BufferedOutputStream out = new BufferedOutputStream(
                new GZIPOutputStream(new FileOutputStream("test.gz")));
        System.out.println("Writing file");

        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();

        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
        String s;

        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }
    }
}
