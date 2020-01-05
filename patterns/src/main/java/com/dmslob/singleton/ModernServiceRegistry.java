package com.dmslob.singleton;

import com.dmslob.singleton.canonical.NamedService;

import java.util.HashMap;
import java.util.Map;

public class ModernServiceRegistry {

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
