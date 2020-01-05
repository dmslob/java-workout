package com.dmslob.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBufferExample {

    private static void swap(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();

            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "TestBuffer".toCharArray();
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 2);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        charBuffer.put(data);
        System.out.println(charBuffer.rewind());

        swap(charBuffer);
        System.out.println(charBuffer.rewind());
        swap(charBuffer);
        System.out.println(charBuffer.rewind());
    }
}
