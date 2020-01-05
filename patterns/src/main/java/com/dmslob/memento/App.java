package com.dmslob.memento;

public class App {

//    Benefits of Memento:
//    - Memento design pattern provides a way of storing the internal state of an object in a separate object without violating rule of design pattern.
//    - Memento pattern records the objects state without understanding encapsulation.
//    - It eliminates the requirement for multiple creation of the same object for the sole purpose of saving its state.
//    - It mainly provides a recovery mechanism in case failure occurs.
//    - This design pattern provides a way to maintain history of an object’s life cycle.
//    - Help us when we want to restore back an abject to its previous state.
//    - Help us when we want to maintain a history of states of an object.
    public static void main(String[] args) {

        WordDocument document = new WordDocument(1, "My Article");
        document.setDescription("ABC");
        System.out.println(document);

        WordDocumentMemento memento = document.createMemento();

        document.setDescription("XYZ");
        System.out.println(document);

        document.restore(memento);
        System.out.println(document);
    }
}
