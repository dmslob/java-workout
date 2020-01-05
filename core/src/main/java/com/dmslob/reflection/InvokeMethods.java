package com.dmslob.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeMethods {

    public static void main(String[] args) {

    }

    static void invokeSetter() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setMethod = Operations.class.getMethod("setPriorityScore", String.class);
        Operations operationsInstance = new Operations();
        setMethod.invoke(operationsInstance, "HIGH");

        System.out.println(operationsInstance.getPriorityScore());
    }
}

class Operations {
    private String priorityScore;

    public String getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(String priorityScore) {
        this.priorityScore = priorityScore;
    }
}
