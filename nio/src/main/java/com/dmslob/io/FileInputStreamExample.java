package com.dmslob.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample {

    public static void main(String[] args) {
        int b;
        try {
            File f = new File("Build.xml");
            FileInputStream is = new FileInputStream(f);
            while ((b = is.read()) != -1) {
                System.out.print(b);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }
}
