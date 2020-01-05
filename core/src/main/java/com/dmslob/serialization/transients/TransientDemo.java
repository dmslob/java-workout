package com.dmslob.serialization.transients;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TransientDemo {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws Exception {

        Logon logon = new Logon("Hulk", "myLittlePony");
        System.out.println("logon a = " + logon);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Logon.out"));
        o.writeObject(logon);
        o.close();

        TimeUnit.SECONDS.sleep(1);

        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
        System.out.println("Recovering object at " + new Date());
        logon = (Logon) in.readObject();
        System.out.println("logon a = " + logon);
    }
}

class Logon implements Serializable {

    private Date date = new Date();
    private String username;
    private transient String password; // will be null after deserialization

    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }

    @Override
    public String toString() {
        return "logon info: \n   username: " + username +
                "\n   date: " + date + "\n   password: " + password;
    }
}