package com.dmslob.queue.v3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> gueue = new ArrayBlockingQueue(FILE_QUEUE_SIZE);

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdkl.8.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            Runnable enumerator = () -> {
                try {
                    enumerate(new File(directory));
                    gueue.put(DUMMY);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            };

            new Thread(enumerator).start();
            for (int i = 1; i <= SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            {
                                File file = gueue.take();
                                if (file == DUMMY) {
                                    gueue.put(file);
                                    done = true;
                                } else {
                                    search(file, keyword);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                };
                new Thread(searcher).start();
            }
        }
    }

    /* Рекурсивно перечисляет все файлы в заданном
     * каталоге и его подкаталогах
     * directory Исходный каталог
     */
    public static void enumerate(File directory)
            throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                gueue.put(file);
            }
        }
    }

    /* *
     * Осуществляет поиск заданного ключевого слова в файлах
     * file Искомый файл
     * keyword Ключевое слово для поиска
     */
    public static void search(File file, String keyword) throws IOException {

        try (Scanner in = new Scanner(file, "UTF-8")) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;

                String line = in.nextLine();
                System.out.println("Line " + lineNumber + " : " + line);

                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file.getPath(),
                            lineNumber, line);
                }
            }
        }
    }
}
