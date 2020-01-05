package com.dmslob.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWDictionary {
    private final Map<String, Data> dataMap = new TreeMap<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return dataMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public String[] allKeys() {
        readLock.lock();
        try {
            return (String[]) dataMap.keySet().toArray();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return dataMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            dataMap.clear();
        } finally {
            writeLock.unlock();
        }
    }
}

class Data {
}
