package com.dmslob.nio;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class LockingFileExample {

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("test.txt");
        FileLock fl = fos.getChannel().tryLock();

        if (fl != null) {
            System.out.println("File locked");
            TimeUnit.MILLISECONDS.sleep(100);
            fl.release();
            System.out.println("File unlocked");
        }
        fos.close();
    }
}
