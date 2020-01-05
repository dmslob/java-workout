package com.dmslob.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {

    public static void main(String[] args) {
        int pArray[] = {1, 2, 3, 5, 7, 11, 13, 17};
        try {
            FileOutputStream os = new FileOutputStream("bytewrite.dat");
            for (int i = 0; i < pArray.length; i++) {
                os.write(pArray[i]);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }
}
