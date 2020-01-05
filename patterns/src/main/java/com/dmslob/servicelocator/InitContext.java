package com.dmslob.servicelocator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class InitContext {

    private static final Logger LOGGER = LogManager.getLogger(InitContext.class);

    public Object lookup(String serviceName) {
        if (serviceName.equals("EmailService")) {
            LOGGER.info("Looking up EmailService and creating new one");
            return new EmailService("EmailService");
        }
        if (serviceName.equals("SmsService")) {
            LOGGER.info("Looking up SmsService and creating new one");
            return new SmsService("SmsService");
        }
        return null;
    }
}
