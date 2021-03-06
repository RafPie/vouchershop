package pl.rpietrzak.voucherstore.sales.payment;

import pl.rpietrzak.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}
