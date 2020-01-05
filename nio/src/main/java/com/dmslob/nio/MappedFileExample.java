package com.dmslob.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedFileExample {

    static int length = 0x8FFFFFF;  // 128 MB

    public static void main(String[] args) throws Exception {
        MappedByteBuffer buffer = new RandomAccessFile("test.dat", "rw")
                .getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            buffer.put((byte) 'x');
        }
        System.out.println("Record completed");

        for (int i = length / 2; i < length / 2 + 6; i++) {
            System.out.print((char) buffer.get(i));
        }
    }
}
