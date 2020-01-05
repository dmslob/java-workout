package com.dmslob.exception;

public class LostExceptionDemo {

    public void throwImportantException() throws ImportantException {
        throw new ImportantException();
    }

    public void throwSimpleException() throws SimpleException {
        throw new SimpleException();
    }

    public static void main(String[] args) {
        LostExceptionDemo exceptionDemo = new LostExceptionDemo();
        try {
            try {
                exceptionDemo.throwImportantException();
            } finally {
                exceptionDemo.throwSimpleException();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

class SimpleException extends Exception {
    public String toString() {
        return "Simple exception";
    }
}

class ImportantException extends Exception {
    public String toString() {
        return "Important exception";
    }
}