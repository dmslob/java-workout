package com.dmslob.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ByteOrderExample {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[12]);
        //asCharBuffer()

        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
        // direct order
        buffer.rewind();
        //order()
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));

        // inverse order
        buffer.rewind();
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
    }
}
