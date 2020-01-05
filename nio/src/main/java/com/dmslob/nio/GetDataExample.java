package com.dmslob.nio;

import java.nio.ByteBuffer;

public class GetDataExample {

    private static final int BUFSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
        buffer.asCharBuffer().put("Test");

        // As char
        char c;
        while ((c = buffer.getChar()) != 0) {
            System.out.print(c);
        }
        System.out.println();

        // As int
        buffer.rewind();
        buffer.asIntBuffer().put(99157836);
        System.out.println(buffer.getInt());

        // As float
        buffer.rewind();
        buffer.asFloatBuffer().put(99157836);
        System.out.println(buffer.getFloat());
    }
}
