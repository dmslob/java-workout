package com.dmslob.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationDemo {

    @CustomAnnotationMethod(date = "03-21-2019", description = "annotated method")
    public String annotatedMethod() {
        return "nothing";
    }

    public static void main(String[] args) {
        AnnotationDemo demo = new AnnotationDemo();
        demo.runSimpleProcessor();
    }

    public void runSimpleProcessor() {
        MutableClass mutableClass = new MutableClass("DMSLOB");
        mutableClass.getName();
    }

    public void customAnnotationProcessing() {
        Class<AnnotationDemo> annotationDemoClass = AnnotationDemo.class;
        Annotation[] annotations = annotationDemoClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        // Checks if an annotation is present
        if (annotationDemoClass.isAnnotationPresent(CustomAnnotationMethod.class)) {
            // Gets the desired annotation
            Annotation annotation = annotationDemoClass.getAnnotation(CustomAnnotationMethod.class);
            System.out.println(annotation);
        }
        // the same for all methods of the class
        for (Method method : annotationDemoClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CustomAnnotationMethod.class)) {
                Annotation annotation = method.getAnnotation(CustomAnnotationMethod.class);
                System.out.println(annotation);
            }
        }
    }
}