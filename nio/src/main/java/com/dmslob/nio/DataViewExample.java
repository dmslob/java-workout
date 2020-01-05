package com.dmslob.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

public class DataViewExample {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'c'});

        // Byte
        buffer.rewind();
        System.out.println("Byte:");
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get() + ": ");
        }
        System.out.println();

        // Char
        buffer.rewind();
        CharBuffer charBuffer = buffer.asCharBuffer();
        System.out.println("Char:");
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get() + ": ");
        }
        System.out.println();

        // Int
        buffer.rewind();
        IntBuffer intBuffer = buffer.asIntBuffer();
        System.out.println("Int:");
        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.get() + ": ");
        }
        System.out.println();
    }
}
