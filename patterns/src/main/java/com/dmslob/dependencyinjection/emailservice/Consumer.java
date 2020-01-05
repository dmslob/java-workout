package com.dmslob.dependencyinjection.emailservice;

public interface Consumer {
    void processMessages(String msg, String rec);
}
