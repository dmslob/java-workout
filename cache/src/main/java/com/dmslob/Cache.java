package com.dmslob;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private static Map<Object, Entity> storage = new ConcurrentHashMap<>();
    private static long defaultLifetime = 10000L;

    /**
     * Cache entity object. Keeps a reference to cached object and some metadata
     */
    private static class Entity {
        final Object value;
        final long dieAt;

        Entity(Object value, long dieAt) {
            this.value = value;
            this.dieAt = dieAt;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > dieAt;
        }
    }

    public void setDefaultTimeout(long newTimeout) {
        defaultLifetime = newTimeout;
    }

    /**
     * Fetches actual (not expired) object from cache
     */
    public Object get(Object key) {
        Entity e = storage.get(key);
        if (e != null) {
            if (!e.isExpired()) {
                return e.value;
            } else {
                remove(key);
            }
        }
        return null;
    }

    /**
     * Puts a key->value pair into a cache with default lifetime
     */
    public void put(Object key, Object value) {
        put(key, value, defaultLifetime);
    }

    /**
     * Puts a key->value pair into a cache
     */
    public void put(Object key, Object value, long timeout) {
        storage.put(key, new Entity(value, System.currentTimeMillis() + timeout));
    }

    /**
     * Removes keyed value from a cache
     */
    public void remove(Object key) {
        storage.remove(key);
    }

    /**
     * Clears the cache
     */
    public void clear() {
        storage.clear();
    }

    /**
     * Removes expired objects from a cache
     */
    public void expire() {
        for (Object key : storage.keySet()) {
            if (storage.get(key).isExpired()) {
                storage.remove(key);
            }
        }
    }
}
