package com.dmslob.servicelocator;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class Demo {

    public static void main(String[] args) {

        MessageService service = ServiceLocator.getService("EmailService");
        service.execute();

        service = ServiceLocator.getService("SmsService");
        service.execute();
    }
}
