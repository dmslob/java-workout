package com.dmslob.dependencyinjection.emailservice;

public class EmailServiceInjector implements MessageServiceInjector {

    public Consumer getConsumer() {
        return new MyDIApplication(new EmailServiceImpl());
    }
}
