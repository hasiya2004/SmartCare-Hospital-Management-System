package com.smartcare.hospital_api.service.payment;

import java.math.BigDecimal;

public interface PaymentService {
    String processPayment(BigDecimal amount, String currency);
}