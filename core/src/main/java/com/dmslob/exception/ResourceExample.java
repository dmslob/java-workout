package com.dmslob.exception;

import static java.lang.System.err;
import static java.lang.System.setOut;

public class ResourceExample {

    void testResource() throws Exception {
        Resource resource = new Resource();
        try {
            resource.use();
        } finally {
            resource.close();
        }
    }

    boolean getResult() throws Exception {
        Resource resource = new Resource();
        try {
            resource.use();
        } catch (Exception e) {
            return true;
        } finally {
            System.out.println("Finaly");
        }
        return false;
    }

    public static void main(String args[]) throws Exception {
        ResourceExample resourceExample = new ResourceExample();

        try {
            System.out.println(resourceExample.getResult());
            resourceExample.testResource();
        } catch (Exception ex) {
            err.println("Exception encountered: " + ex.toString());
            final Throwable[] suppressedExceptions = ex.getSuppressed();
            final int numSuppressed = suppressedExceptions.length;
            if (numSuppressed > 0) {
                err.println("There are " + numSuppressed + " suppressed exceptions:");
                for (final Throwable exception : suppressedExceptions) {
                    err.println("tt" + exception.toString());
                }
            }
        }
    }
}
