package com.dmslob.singleton.canonical;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {

    private static final ServiceRegistry INSTANCE = new ServiceRegistry();

    public static ServiceRegistry getInstance() {
        return INSTANCE;
    }

    private final Map<String, NamedService> services = new HashMap<>();

    public void register(NamedService service) {
        String name = service.getName();
        System.out.println("Register service with name: " + name);
        services.put(name, service);
    }

    public NamedService getService(String name) {
        return services.get(name);
    }
}
