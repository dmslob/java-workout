package com.dmslob.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Snippet {

    /**
     * Read file as list of strings
     *
     * @param filename the filename to read from
     * @return list of strings
     * @throws IOException
     */
    public static List<String> readLines(String filename) throws IOException {
        return Files.readAllLines(new File(filename).toPath());
    }

    /**
     * List directories
     *
     * @param path the path where to look
     * @return array of File
     */
    public static File[] listDirs(String path) {
        return new File(path).listFiles(File::isDirectory);
    }

    /**
     * List files in directory
     *
     * @param folder the path where to look
     * @return array of File
     */
    public static File[] listFilesInDir(final File folder) {
        return folder.listFiles(File::isFile);
    }

    /**
     * Recursively list all the files in directory
     *
     * @param path the path to start the search from
     * @return list of all files
     */
    public static List<File> listAllFiles(String path) {
        List<File> all = new ArrayList<>();
        File[] list = new File(path).listFiles();
        if (list != null) {  // In case of access error, list is null
            for (File f : list) {
                if (f.isDirectory()) {
                    all.addAll(listAllFiles(f.getAbsolutePath()));
                } else {
                    all.add(f.getAbsoluteFile());
                }
            }
        }
        return all;
    }

    /**
     * Zip single file
     *
     * @param srcFilename the filename of the source file
     * @param zipFilename the filename of the destination zip file
     * @throws IOException
     */
    public static void zipFile(String srcFilename, String zipFilename) throws IOException {
        File srcFile = new File(srcFilename);
        try (
                FileOutputStream fileOut = new FileOutputStream(zipFilename);
                ZipOutputStream zipOut = new ZipOutputStream(fileOut);
                FileInputStream fileIn = new FileInputStream(srcFile);
        ) {
            ZipEntry zipEntry = new ZipEntry(srcFile.getName());
            zipOut.putNextEntry(zipEntry);
            final byte[] bytes = new byte[1024];
            int length;
            while ((length = fileIn.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

    /**
     * Zip multiples files
     *
     * @param srcFilenames array of source file names
     * @param zipFilename  the filename of the destination zip file
     * @throws IOException
     */
    public static void zipFiles(String[] srcFilenames, String zipFilename) throws IOException {
        try (
                FileOutputStream fileOut = new FileOutputStream(zipFilename);
                ZipOutputStream zipOut = new ZipOutputStream(fileOut);
        ) {
            for (int i = 0; i < srcFilenames.length; i++) {
                File srcFile = new File(srcFilenames[i]);
                try (FileInputStream fileIn = new FileInputStream(srcFile)) {
                    ZipEntry zipEntry = new ZipEntry(srcFile.getName());
                    zipOut.putNextEntry(zipEntry);
                    final byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fileIn.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                }
            }
        }
    }
}
