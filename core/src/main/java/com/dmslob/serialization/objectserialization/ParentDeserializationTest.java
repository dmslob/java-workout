package com.dmslob.serialization.objectserialization;

import java.io.*;

public class ParentDeserializationTest {

    public static void main(String[] args) {

        try {
            System.out.println("Creating...");

            Child c = new Child(1);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            c.field = 10;
            c.setName("n1");
            System.out.println("Serializing...");
            oos.writeObject(c);
            oos.flush();
            baos.flush();
            oos.close();
            baos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            System.out.println("Deserializing...");
            Child c1 = (Child) ois.readObject();
            System.out.println("c1.i=" + c1.getI());
            System.out.println("c1.field=" + c1.getField());
            System.out.println("c1.name=" + c1.getName());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static class Parent {

        protected int field;

        protected Parent() {
            field = 5;
            System.out.println("Parent::Constructor");
        }

        public int getField() {
            return field;
        }
    }

    public static class Child extends Parent implements Serializable {

        private String name;
        protected int i;

        public Child(int i) {
            this.i = i;
            System.out.println("Child::Constructor");
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getI() {
            return i;
        }
    }
}
