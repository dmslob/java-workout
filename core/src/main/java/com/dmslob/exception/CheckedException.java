package com.dmslob.exception;


import java.sql.SQLException;

public class CheckedException {

    // There are no throws: exceptions are not declared
    public static void main(String[] args) {
        doThrow(new SQLException());
    }

    static void doThrow(Exception e) {
        CheckedException.<RuntimeException>doThrow0(e);
    }

    @SuppressWarnings("unchecked")
    static <E extends Exception> void doThrow0(Exception e) throws E {
        throw (E) e;
    }
}
