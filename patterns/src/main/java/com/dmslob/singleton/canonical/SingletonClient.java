package com.dmslob.singleton.canonical;

public class SingletonClient implements NamedService {

    public static void main(String[] args) {
        new SingletonClient().start();
    }

    public void start() {
        ServiceRegistry.getInstance().register(this);
    }

    public String getName() {
        return "MAIN";
    }
}
