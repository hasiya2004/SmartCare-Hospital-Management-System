package com.smartcare.hospital_api.controller;

import com.smartcare.hospital_api.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService cashPayment;
    private final PaymentService cardPayment;
    private final PaymentService onlinePayment;

    public PaymentController(@Qualifier("cashPayment") PaymentService cashPayment,
                             @Qualifier("cardPayment") PaymentService cardPayment,
                             @Qualifier("onlinePayment") PaymentService onlinePayment) {
        this.cashPayment = cashPayment;
        this.cardPayment = cardPayment;
        this.onlinePayment = onlinePayment;
    }

    @PostMapping("/{method}")
    public String pay(@PathVariable String method,
                      @RequestParam BigDecimal amount,
                      @RequestParam(defaultValue = "LKR") String currency) {
        return switch (method.toLowerCase()) {
            case "cash" -> cashPayment.processPayment(amount, currency);
            case "card" -> cardPayment.processPayment(amount, currency);
            case "online" -> onlinePayment.processPayment(amount, currency);
            default -> "Invalid payment method. Use cash, card, or online.";
        };
    }
}