package com.dmslob.init;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Invoice {

    private static double price;

    static {
        //Unhandled Exception throw new Exception("Static init block error");
        try {
            price = 56 / 0;
        } catch (Exception e) {
            log.error("Static init block error {}", e.getMessage());
            price = 0;
        }
    }

    public Invoice() throws Exception {
        throw new Exception("Error when building the Invoice");
    }
}
