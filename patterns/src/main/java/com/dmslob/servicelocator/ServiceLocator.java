package com.dmslob.servicelocator;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class ServiceLocator {

    private static ServiceCache serviceCache = new ServiceCache();

    private ServiceLocator() {
    }

    public static MessageService getService(String serviceName) {
        MessageService service = serviceCache.getService(serviceName);

        if (service != null) {
            return service;
        } else {
          /*
           * If we are unable to retrive anything from cache, then lookup the service and add it in the
           * cache map
           */
            InitContext initContext = new InitContext();
            service = (MessageService) initContext.lookup(serviceName);
            if (service != null) {
                serviceCache.addService(service);
            }
            return service;
        }
    }
}
