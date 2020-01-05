package com.dmslob.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class EncodingExample {

    private static final int BUFSIZE = 1024;

    public static void main(String[] args) throws Exception {
        FileChannel fc = new FileOutputStream("data.txt").getChannel();

        fc.write(ByteBuffer.wrap("First part".getBytes()));
        fc.close();

        // Read file
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
        fc.read(buffer);
        buffer.flip();

        // Incorrect
        System.out.println(buffer.asCharBuffer());

        // decode
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");

        System.out.println("Decoding in " + encoding + ": " +
                Charset.forName(encoding).decode(buffer));

        // 2. Coding for write
        fc = new FileOutputStream("data.txt").getChannel();

        fc.write(ByteBuffer.wrap("First part".getBytes("UTF-16BE")));
        fc.close();

        // Read
        fc = new FileInputStream("data.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();

        // Correct
        System.out.println(buffer.asCharBuffer());

        // 3. Coding with CharBuffer
        fc = new FileOutputStream("data.txt").getChannel();
        buffer = ByteBuffer.allocate(BUFSIZE);
        buffer.asCharBuffer().put("First part");
        fc.write(buffer);
        fc.close();

        // Read
        fc = new FileInputStream("data.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();

        // Correct
        System.out.println(buffer.asCharBuffer());
    }
}
