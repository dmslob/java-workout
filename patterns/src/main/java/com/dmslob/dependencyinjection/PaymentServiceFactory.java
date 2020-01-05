package com.dmslob.dependencyinjection;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class PaymentServiceFactory {

    public PaymentService getPaymentService(Payment payment) {
        if (payment == Payment.CASH) {
            return new CashPaymentService();
        }

        if (payment == Payment.CARD) {
            return new CardPaymentService();
        }
        return null;
    }
}
