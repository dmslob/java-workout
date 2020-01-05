package com.dmslob.factorymethod;

import com.dmslob.factorymethod.canonical.Document;
import com.dmslob.factorymethod.canonical.DocumentFactory;
import com.dmslob.factorymethod.canonical.JsonDocument;

import java.util.function.Function;

public class ModernFactoryMethodClient {

    public static void main(String[] args) {
        DocumentFactory factory = JsonDocument::new;
        printUserDetails(factory.create("USER"));

        Function<String, Document> plainFactory = JsonDocument::new;
        printUserDetails(plainFactory.apply("USER"));
    }

    private static void printUserDetails(Document document) {
        document.addField("name", "Mikalai");
        document.addField("surname", "Alimenkou");
        System.out.println(document);
    }
}
