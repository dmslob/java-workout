package com.dmslob.serialization.serializationproxy;

public class SerializationDemo {

    public static void main(String[] args) {

        Person person = new Person("User1", 1, 22);
        final String fileName = "person.ser";

        System.out.println("serializing ...");
        Util.serialzeObject(person, fileName);

        try {
            System.out.println("deserializing ...");
            person = (Person) Util.deSerialzeObject(fileName);
            System.out.println("id " + person.getId() +
                    " Name " + person.getName() +
                    " Age " + person.getAge());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
