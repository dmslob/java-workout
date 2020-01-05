package com.dmslob.clone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CloneDemo {

    private static final Logger LOGGER = LogManager.getLogger(CloneDemo.class);

    public static void main(String[] args) {
        CloneDemo demo = new CloneDemo();
        demo.shallowCopy();
        //demo.deepCopy();
        //demo.cloningCollection();
    }

    public void shallowCopy() {
        Rectangle rec = new Rectangle(30, 60);
        try {
            Rectangle copy = rec.clone();

            boolean isReferenceEquals = (rec == copy);
            LOGGER.info("isReferenceEquals: " + isReferenceEquals);

            boolean isClassEquals = (rec.getClass() == copy.getClass());
            LOGGER.info("isClassEquals: " + isClassEquals);

            boolean isEquals = rec.equals(copy);
            LOGGER.info("isEquals: " + isEquals);

            //Updating fields in original object
            rec.setHeight(100);
            rec.setWidth(45);

            LOGGER.info("Original object :" + rec);
            LOGGER.info("Cloned object  :" + copy);

        } catch (CloneNotSupportedException ex) {
            LOGGER.debug("Cloning is not supported for this object");
        }
    }

    public void deepCopy() {
        Programmer javaGuru = new Programmer("John", 31);
        javaGuru.addCertificates("OCPJP");
        javaGuru.addCertificates("OCMJD");
        javaGuru.addCertificates("PMP");
        javaGuru.addCertificates("CISM");

        Programmer clone = javaGuru.clone();

        javaGuru.addCertificates("Oracle DBA");
        LOGGER.info("Real Java Guru     : {}", javaGuru);
        LOGGER.info("Clone of Java Guru : {}", clone);
    }

    public void cloningCollection() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Joe", "Manager"));
        employeeList.add(new Employee("Tim", "Developer"));
        employeeList.add(new Employee("Frank", "Developer"));

        // creating copy of Collection using copy constructor
        List<Employee> employeeListCopy = new ArrayList<>(employeeList);

        LOGGER.info("Original Collection {}", employeeList);
        LOGGER.info("Copy of Collection  {}", employeeListCopy);

        Iterator<Employee> itr = employeeList.iterator();
        while (itr.hasNext()) {
            itr.next().setDesignation("staff");
        }

        LOGGER.info("Original Collection after modification  {}", employeeList);
        LOGGER.info("Copy of Collection without modification {}", employeeListCopy);
    }
}
