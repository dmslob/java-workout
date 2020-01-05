package com.dmslob.io;

import java.io.*;

public class ByteArrayStream {

    public static void main(String[] args) {
        byte[] array = new byte[100];
        for (int i = 0; i < 100; i++) {
            array[i] = (byte) (Math.random() * 100);
        }
        try {
            InputStream in = new ByteArrayInputStream(array);
            OutputStream out = new ByteArrayOutputStream();
            while (in.available() > 0) {
                out.write(in.read());
            }
            //out.wait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
