package com.dmslob.factorymethod.canonical;

public class JsonDocumentFactory implements DocumentFactory {
    public Document create(String name) {
        return new JsonDocument(name);
    }
}
