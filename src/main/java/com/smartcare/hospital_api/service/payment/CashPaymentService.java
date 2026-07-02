package com.smartcare.hospital_api.service.payment;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service("cashPayment")
public class CashPaymentService implements PaymentService {
    @Override
    public String processPayment(BigDecimal amount, String currency) {
        return "Payment of " + amount + " " + currency + " processed via Cash. Please hand over the cash to the counter.";
    }
}