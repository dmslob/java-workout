package com.dmslob.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileExample {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        int dataArr[] = {12, 31, 56, 23, 27, 1, 43, 65, 4, 99};
        try {
            RandomAccessFile rf = new RandomAccessFile("temp.txt", "rw");

            for (int i = 0; i < dataArr.length; i++) {
                rf.writeInt(dataArr[i]);
            }

            for (int i = dataArr.length - 1; i >= 0; i--) {
                rf.seek(i * 4);   // int 4 bytes
                System.out.println(rf.readInt());   // read reverse
            }

            rf.close();
        } catch (IOException e) {
            System.out.println("File access error: " + e);
        }
    }
}
