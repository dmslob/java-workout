package com.dmslob.exception;

public class ResourceExampleWithTryResource {

    public static void main(String args[]) throws Exception {
        try (Resource resource = new Resource()) {
            resource.use();
        }
    }
}
