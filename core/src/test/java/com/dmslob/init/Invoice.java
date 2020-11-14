package com.dmslob.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Invoice {

    private static final Logger LOGGER = LogManager.getLogger(Invoice.class);

    private static double price;

//    static {
//        /** Unhandled Exception
//        throw new Exception("Static init block error"); */
//        try {
//            price = 56 / 0;
//        } catch (Exception e) {
//            LOGGER.error("Static init block error", e.fillInStackTrace());
//            price = 0;
//        }
//    }

    public Invoice() throws Exception {
        throw new Exception("Error when building the Invoice");
    }
}
