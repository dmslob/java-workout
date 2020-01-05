package com.dmslob.factorymethod.canonical;

public interface Document {

    String getName();

    void addField(String name, String value);

    String toString();
}
