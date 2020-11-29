package com.dmslob.types;

public class PassByValue {

    public class Dog {
        private String name;

        public Dog() {
        }

        public Dog(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    void renameDog(Dog dog) {
        dog = new Dog();
        dog.setName("Bob");
    }
}
