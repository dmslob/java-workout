package com.dmslob.serialization.serializationproxy;

import java.io.*;

public class Util {

    /**
     * Method used for serialization
     *
     * @param obj
     * @param fileName
     */
    public static void serialzeObject(Object obj, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(obj);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used for deserializing
     *
     * @param fileName
     * @return
     * @throws ClassNotFoundException
     */
    public static Object deSerialzeObject(String fileName) throws ClassNotFoundException {
        Object obj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            obj = ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
