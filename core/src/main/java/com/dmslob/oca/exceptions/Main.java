package com.dmslob.oca.exceptions;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        //String result = exceptions();
        //System.out.println(result);
        //eatCarrot();
        //printException();

        badPractice(); // NullPointerException
    }

    static void badPractice() {
        String textInFile = null;
        try {
            readInFile();
        } catch (IOException e) {
            if ("java.io.IOException".equals(e.getClass().getName())) {
                System.out.println("OK");
            }
            // ignore exception
        }
        // imagine many lines of code here
        //System.out.println(textInFile.replace(" ", ""));
    }

    private static void readInFile() throws IOException {
        throw new IOException("Some I/O error");
    }

    static void printException() {
        try {
            eatCarrot();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void eatCarrot() throws Exception {
        throw new Exception("Message");
    }

    static void test1() {
        String[] animals = new String[0];
        try {
            System.out.println(animals[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ups...");
        }

        new Main().visitPorcupine();

        try {
            fall();
        } catch (RuntimeException e) {
            System.out.println("RuntimeException");
        } finally {
            System.exit(0);
            System.out.println("Finally Ups...");
        }
    }

    static void test2() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            throw new RuntimeException();
        } finally {
            throw new RuntimeException();
            //throw new Exception(); // compile error
        }
    }

    static String exceptions() {
        String result = "";
        String v = null;
        try {
            try {
                result += "before";
                v.length();
                result += "after";
            } catch (NullPointerException e) {
                result += "catch";
                throw new RuntimeException();
            } finally {
                result += "finally";
                throw new Exception();
            }
        } catch (Exception e) {
            result += "done";
        }
        return result;
    }

    static void fall() {
        throw new RuntimeException();
    }

    static void checkedFall() throws IOException {
        throw new IOException();
    }

    public void visitPorcupine() {
        try {
            fall();
            checkedFall();
        } catch (IOException e) {// first catch block
            System.out.println("try back later");
        } catch (Exception e) {// second catch block
            System.out.println("not today");
        }
    }
}

class Hopper {

    public void hop() throws Exception {
    }
}

class Bunny extends Hopper {

    public void hop() throws CanNotHopException {
    }
}


class AnimalsOutForAWalk extends RuntimeException {
}

class ExhibitClosed extends RuntimeException {
}

class ExhibitClosedForLunch extends ExhibitClosed {
}

class CanNotHopException extends Exception {
}