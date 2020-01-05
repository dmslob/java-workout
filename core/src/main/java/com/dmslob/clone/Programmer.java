package com.dmslob.clone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Programmer implements Cloneable {

    private static final Logger LOGGER = LogManager.getLogger(Programmer.class);

    private String name;
    private int age;
    private List<String> certifications;

    public Programmer(String name, int age) {
        this.name = name;
        this.age = age;
        this.certifications = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCertificates(String certs) {
        certifications.add(certs);
    }

    @Override
    public String toString() {
        return String.format("%s, %d, Certifications: %s", name, age, certifications.toString());
    }

    @Override
    protected Programmer clone() {
        Programmer clone = null;
        try {
            clone = (Programmer) super.clone();
            clone.certifications = new ArrayList(certifications); //deep copy

        } catch (CloneNotSupportedException ex) {
            LOGGER.error("Error while cloning programmer", ex);
        }
        return clone;
    }
}
