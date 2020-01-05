package com.dmslob.servicelocator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class ServiceCache {

    private static final Logger LOGGER = LogManager.getLogger(ServiceCache.class);

    private final Map<String, MessageService> serviceCache;

    public ServiceCache() {
        serviceCache = new HashMap<>();
    }

    public MessageService getService(String searchService) {
        MessageService cachedService = null;

        for (String serviceName : serviceCache.keySet()) {
            if (serviceName.equals(searchService)) {
                cachedService = serviceCache.get(serviceName);
                LOGGER.info("(cache call) Fetched service {}({}) from cache... !",
                        cachedService.getName(), cachedService.getId());
            }
        }
        return cachedService;
    }

    public void addService(MessageService newService) {
        serviceCache.put(newService.getName(), newService);
    }
}
