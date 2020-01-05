package com.dmslob.io;

import java.io.File;
import java.io.IOException;

public class FileExample {

    public static void main(String[] args) throws IOException {
        File fp = new File("demo.txt");
        /* other:
         File fp = new File("\\test", "demo.txt");
         File fp = new File("d:\\test\\demo.txt");
         File fp = new File("test\\demo.txt");
         */
        if (fp.exists()) {
            System.out.println("file " + fp.getName() + " exist");
        } else {
            if (fp.createNewFile()) {
                System.out.println("File " + fp.getName() + " created");
            }
        }

        if (fp.isFile()) {
            System.out.println("File name:\t" + fp.getName());
            System.out.println("Path:\t" + fp.getPath());
            System.out.println("Absolute path:\t" + fp.getAbsolutePath());
            System.out.println("File size:\t" + fp.length());
            System.out.println("Last modified:\t" + fp.lastModified());
            System.out.println("Can read:\t" + fp.canRead());
            System.out.println("Can write:\t" + fp.canWrite());

            File newName = new File("new.txt");
            fp.renameTo(newName);
            System.out.println("File name:\t" + fp.getName());
            System.out.println("Deleted:\t" + fp.delete());
        }

        // Directory
        File testDirectory = new File("test");
        if (!testDirectory.exists()) {
            testDirectory.mkdir();
        }
        testDirectory.delete();

        File dir = new File(".");
        if (dir.isDirectory()) {
            System.out.println("Directory:\t" + dir.getName());

            // list()
            String[] list = dir.list();
            for (String fileName : list) {
                System.out.println(fileName);
            }

            // listFiles()
            File[] fileList = dir.listFiles();
            for (File file : fileList) {
                System.out.println(file.getName());
            }

            // listRoots()
            File[] rootList = File.listRoots();
            for (File rootName : rootList) {
                System.out.println(rootName);
            }
        }
    }
}
