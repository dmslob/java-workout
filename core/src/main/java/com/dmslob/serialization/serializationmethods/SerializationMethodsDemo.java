package com.dmslob.serialization.serializationmethods;

import java.io.*;

public class SerializationMethodsDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Serial sc1 = new Serial("Test1", "Test2");
        System.out.println("Before:\n" + sc1);

        // Save
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(buf);
        o.writeObject(sc1);

        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        Serial sc2 = (Serial) in.readObject();
        System.out.println("After:\n" + sc2);
    }
}

class Serial implements Serializable {

    private String a;
    private transient String b;

    public Serial(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    @Override
    public String toString() {
        return a + "\n" + b;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b = (String) stream.readObject();
    }
}