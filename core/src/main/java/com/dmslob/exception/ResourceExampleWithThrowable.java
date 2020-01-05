package com.dmslob.exception;

import static java.lang.System.err;

public class ResourceExampleWithThrowable {

    static void addSuppressedException() throws Exception {
        Throwable throwable = null;
        Resource resource = new Resource();
        try {
            resource.use();
        } catch (Exception e) {
            throwable = e;
        } finally {
            try {
                resource.close();
            } catch (Exception e) {
                if (throwable != null) {
                    e.addSuppressed(throwable);
                    throw e;
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        ResourceExampleWithThrowable exampleWithThrowable = new ResourceExampleWithThrowable();
        try {
            exampleWithThrowable.addSuppressedException();
        } catch (Exception ex) {
            err.println("Exception encountered: " + ex.toString());
            final Throwable[] suppressedExceptions = ex.getSuppressed();
            final int numSuppressed = suppressedExceptions.length;
            if (numSuppressed > 0) {
                err.println("There are " + numSuppressed + " suppressed exceptions:");
                for (final Throwable exception : suppressedExceptions) {
                    err.println("" + exception.toString());
                }
            }
        }
    }
}
