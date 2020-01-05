package com.dmslob.serialization.externalizable;

import java.io.*;

public class ExternalizableDemo {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Constructing objects:");
        Blip blip = new Blip("A String ", 47);
        System.out.println(blip);

        // Save
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blip.out"));
        System.out.println("Saving object:");
        o.writeObject(blip);
        o.close();

        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blip.out"));
        System.out.println("Recovering blip:");
        blip = (Blip) in.readObject();
        System.out.println(blip);
    }
}

class Blip implements Externalizable {

    private int i;
    private transient String s;

    public Blip() {
        System.out.println("Blip Constructor");
        // s, i not initialized
    }

    public Blip(String x, int a) {
        System.out.println("Blip(String x, int a)");
        s = x;
        i = a;
        // s & i initialized only in non-default constructor.
    }

    @Override
    public String toString() {
        return s + i;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip.writeExternal");
        // You must do this:
        out.writeObject(s);
        out.writeInt(i);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip.readExternal");
        // You must do this:
        s = (String) in.readObject();
        i = in.readInt();
    }
}

