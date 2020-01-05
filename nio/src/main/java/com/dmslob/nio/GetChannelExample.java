package com.dmslob.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class GetChannelExample {

    private static final int BUFSIZE = 1024;

    public static void main(String[] args) throws Exception {
        // Write to file
        FileChannel fc = new FileOutputStream("data.txt").getChannel();

        fc.write(ByteBuffer.wrap("First part. ".getBytes()));
        fc.close();

        // Append to end
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size()); // position to end
        fc.write(ByteBuffer.wrap("Second part".getBytes()));
        fc.close();

        // Read file
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
        fc.read(buffer);
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
    }
}
