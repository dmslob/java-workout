package com.dmslob.io;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class DirFilter implements FilenameFilter {

    private String filter;

    public DirFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(filter);
    }
}

public class FilenameFilterExample {

    public static void main(String[] args) throws IOException {
        File fp = new File("test.txt");
        if (!fp.exists()) {
            fp.createNewFile();
        }

        File dir = new File(".");
        String filter = ".txt";
        // list()
        String[] list = dir.list(new DirFilter(filter));
        for (String fileName : list) {
            System.out.println(fileName);
        }
        fp.delete();
    }
}
