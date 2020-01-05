package com.dmslob.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopyExample {

    private static final int BUFSIZE = 1024;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File f = new File("Build.xml");
        FileChannel in = new FileInputStream(f).getChannel();
        FileChannel out = new FileOutputStream(f).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);

        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
    }
}
