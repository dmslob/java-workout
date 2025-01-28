package com.dmslob.exceptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ExceptionsTest {

    /*
     * Java’s lambda functions syntax does not allow to specify the
     * checked exceptions (unless those are defined by @FunctionalInterface itself)
     * which may be thrown.
     */
    public void readFile() throws IOException {
        run(() -> {
            // Compile error: 'Unhandled exception type IOException'
            //Files.readAllBytes(new File("some.txt").toPath());
        });
    }

    public void run(final Runnable runnable) {
        runnable.run();
    }

    public void read(final Readable readable) throws IOException {
        readable.read();
    }

    public void read() throws IOException {
        read(() -> {
            Files.readAllBytes(new File("some.txt").toPath());
        });
    }

    @FunctionalInterface
    interface Readable {
        void read() throws IOException;
    }
}
