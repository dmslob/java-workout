package com.dmslob.generic.fail;

import java.util.LinkedList;
import java.util.List;

public class BaseFail {
    public static void main(String[] args) {
        Holder cameraHolder = new CameraHolder();
        cameraHolder.save(new Camera("Sony"));
        cameraHolder.save(new Camera("Cannon"));
        List<Camera> cameras = cameraHolder.get();
        System.out.println(cameras);
    }
}

abstract class Product {
    protected String name;
}

class Camera extends Product {
    Camera(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "name='" + name + '\'' +
                '}';
    }
}

interface Holder<T extends Product> {
    void save(T t);

    List<T> get();
}

class CameraHolder implements Holder {
    private List<Product> cameras = new LinkedList<>();

    @Override
    public void save(Product camera) {
        cameras.add(camera);
    }

    @Override
    public List<Product> get() {
        return cameras;
    }
}
