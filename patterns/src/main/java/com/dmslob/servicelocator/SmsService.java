package com.dmslob.servicelocator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class SmsService implements MessageService {

    private static final Logger LOGGER = LogManager.getLogger(SmsService.class);

    private final String serviceName;
    private final int id;

    public SmsService(String serviceName) {
        this.serviceName = serviceName;

        this.id = (int) Math.floor(Math.random() * 1000) + 1;
    }

    @Override
    public String getName() {
        return serviceName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void execute() {
        LOGGER.info("Service {} is now executing with id {}", getName(), getId());
    }
}
