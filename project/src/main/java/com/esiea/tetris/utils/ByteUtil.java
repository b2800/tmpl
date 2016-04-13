package com.esiea.tetris.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteUtil {

    public static byte[] toByteArray(Object obj){
        try{
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(obj);
            return b.toByteArray();
        }catch(Exception e){
        }
        return new byte[0];
    }

    public static Object fromByteArray(byte[] bytes){
        try{
            ByteArrayInputStream b = new ByteArrayInputStream(bytes);
            ObjectInputStream o = new ObjectInputStream(b);
            return o.readObject();
        } catch (IOException | ClassNotFoundException e){
        }
        return new byte[0];
    }
}
