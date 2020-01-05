package com.dmslob.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ReadWriteDataExample {

    private static final int BUFSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
        IntBuffer intBuffer = buffer.asIntBuffer();

        intBuffer.put(new int[]{25, 86, 34, 58});
        intBuffer.put(2, 123);
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
