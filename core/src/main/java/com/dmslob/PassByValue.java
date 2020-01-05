package com.dmslob;

public class PassByValue {

    private class Dog {
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

    void foo(Dog dog) {
        //dog.setName("Bob");
        dog = new Dog();
        dog.setName("Bob");
    }

    public static void main(String[] args) {
        PassByValue demo = new PassByValue();
        Dog aDog = new PassByValue().new Dog("Max");
        Dog oldDog = aDog;
        demo.foo(aDog);

        System.out.println(aDog.getName());
        System.out.println(oldDog.getName());
    }
}
