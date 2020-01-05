package com.dmslob.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamExample {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        // Write file
        try {
            FileOutputStream os = new FileOutputStream(new File("data.txt"));
            DataOutputStream ods = new DataOutputStream(os);
            ods.writeInt(48);
            ods.writeFloat(3.14159f);
            ods.writeBoolean(true);
            ods.writeLong(725624);
            ods.close();
        } catch (IOException e) {
            System.out.print("File write error: " + e);
        }
        // Read from file
        try {
            FileInputStream is = new FileInputStream(new File("data.txt"));
            DataInputStream ids = new DataInputStream(is);

            System.out.println(ids.readInt());
            System.out.println(ids.readFloat());
            System.out.println(ids.readBoolean());
            System.out.println(ids.readLong());
            ids.close();
        } catch (IOException e) {
            System.out.print("Error: " + e);
        }
    }
}
