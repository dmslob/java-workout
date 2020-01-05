package com.dmslob.dependencyinjection.emailservice;

public class MyMessageDITest {

    public static void main(String[] args) {

        String msg = "Hi Bob";
        String email = "bob@abc.com";
        String phone = "4088888888";
        MessageServiceInjector serviceInjector = null;
        Consumer consumer = null;

        //Send email
        serviceInjector = new EmailServiceInjector();
        consumer = serviceInjector.getConsumer();
        consumer.processMessages(msg, email);

        //Send SMS
        serviceInjector = new SMSServiceInjector();
        consumer = serviceInjector.getConsumer();
        consumer.processMessages(msg, phone);
    }
}
