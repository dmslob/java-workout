package com.dmslob.dependencyinjection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class CardPaymentService implements PaymentService {

    private static final Logger LOGGER = LogManager.getLogger(CardPaymentService.class);

    @Override
    public void pay(Product product) {
        LOGGER.info("Payed with card {}", product);
    }
}
