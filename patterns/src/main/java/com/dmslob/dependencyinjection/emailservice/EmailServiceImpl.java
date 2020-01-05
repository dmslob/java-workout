package com.dmslob.dependencyinjection.emailservice;

public class EmailServiceImpl implements MessageService {

    public void sendMessage(String msg, String rec) {
        //logic to send email
        System.out.println("Email sent to " + rec + " with Message=" + msg);
    }
}
