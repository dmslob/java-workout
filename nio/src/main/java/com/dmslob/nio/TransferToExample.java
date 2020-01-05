package com.dmslob.nio;

import java.io.*;
import java.nio.channels.FileChannel;

public class TransferToExample {

    public static void main(String[] args) throws Exception {
        File f = new File("Build.xml");
        FileChannel in = new FileInputStream(f).getChannel();
        FileChannel out = new FileOutputStream(f).getChannel();

        in.transferTo(0, in.size(), out);
        //out.transferFrom(in, 0, in.size());
    }
}
