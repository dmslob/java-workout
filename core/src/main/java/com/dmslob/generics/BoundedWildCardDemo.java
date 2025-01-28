package com.dmslob.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoundedWildCardDemo {

    public static void main(String[] args) {
        BoundedWildCardDemo demo = new BoundedWildCardDemo();

        Container<Item> items;
        Container<NewItem> newItems;
        Container<OldItem> oldItems;

        List<Item> itemList = new ArrayList<>();
        List<NewItem> newItemList = new ArrayList<>();
        List<OldItem> oldItemList = new ArrayList<>();
        List<Serializable> serializableList = new ArrayList<>();

        demo.copy(itemList, itemList);
        demo.copy(itemList, serializableList);
        demo.copy(newItemList, itemList);
        demo.copy(oldItemList, itemList);

        // Wrong 2d argument type
        //demo.copy(itemList, newItemList);
        //demo.copy(itemList, oldItemList);

        OldPhone oldPhone1 = new OldPhone();
        OldPhone oldPhone2 = new OldPhone();
        NewPhone newPhone = new NewPhone();

        oldPhone1.compareTo(oldPhone2);
        //oldPhone1.compareTo(newPhone); //compile error
    }

    public void copy(List<? extends Item> src, List<? super Item> dest) {
        for (Item p : src) {
            dest.add(p);
        }
    }
}

class Item implements Serializable {
    private String name;
    private Double price;
}

class NewItem extends Item {
    private int pixels;
}

class OldItem extends Item {
    private String model;
}

class Container<T extends Item> {
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}

abstract class Phone<T extends Phone<T>> implements Comparable<T> {
    @Override
    public int compareTo(T o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    abstract void subCampare(T p);
}

class OldPhone extends Phone<OldPhone> {
    void subCampare(OldPhone c) {
    }
}

class NewPhone extends Phone {
    void subCampare(Phone p) {
        if (p instanceof NewPhone) {
        }
    }
}

