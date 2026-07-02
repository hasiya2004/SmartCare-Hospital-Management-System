package com.smartcare.hospital_api.service.payment;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service("onlinePayment")
public class OnlinePaymentService implements PaymentService {
    @Override
    public String processPayment(BigDecimal amount, String currency) {
        return "Payment of " + amount + " " + currency + " processed via Online. Check your banking app.";
    }
}