package pl.rpietrzak.voucherstore.sales.payment;

import pl.rpietrzak.voucherstore.sales.ordering.Reservation;

public interface PaymentGateway {
    PaymentDetails register(Reservation reservation);

    boolean isTrusted(PaymentUpdateStatusRequest paymentUpdateStatusRequest);
}
