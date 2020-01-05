package com.dmslob.templatemethod.canonical;

public class TemplateMethodClient {

    public static void main(String[] args) {
        User mikalai = new LoggingUserRestorer().restore("Mikalai:32");
        System.out.println("Mikalai is restored: " + mikalai);
    }
}
