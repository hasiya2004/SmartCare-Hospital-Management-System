package com.smartcare.hospital_api.service.payment;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service("cardPayment")
public class CardPaymentService implements PaymentService {
    @Override
    public String processPayment(BigDecimal amount, String currency) {
        return "Payment of " + amount + " " + currency + " processed via Card. Swipe/Tap your card.";
    }
}