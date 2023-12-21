package com.example.demo.service;

import java.util.Random;

public class PaymentService {
    
    public boolean processPayment(String creditCardNumber, double amount) {
        boolean paymentSuccess = makePaymentRequest(creditCardNumber, amount);

        return paymentSuccess;
    }

    private boolean makePaymentRequest(String creditCardNumber, double amount) {

        Random random = new Random();
        boolean paymentSuccess = random.nextBoolean();

        System.out.println("Payment processing result: " + (paymentSuccess ? "Success" : "Failure"));

        return paymentSuccess;
    }
}