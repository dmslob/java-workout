package com.dmslob.serialization.objectserialization;

import java.io.*;

public class ObjectSerializationDemo {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        Worm w1 = new Worm(3, 'a');
        System.out.println("w1 = " + w1);

        // To file
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(w1);
        out.close(); // Also flushes output

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) in.readObject();
        Worm w2 = (Worm) in.readObject();
        System.out.println(s + "w2 = " + w2);

        // To memory
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        ObjectOutputStream out2 = new ObjectOutputStream(bout);
//        out2.writeObject("Worm storage\n");
//        out2.writeObject(w1);
//        out2.flush();
//
//        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
//        s = (String) in2.readObject();
//        Worm w3 = (Worm) in2.readObject();
//        System.out.println(s + "w3 = " + w3);
    }
}
