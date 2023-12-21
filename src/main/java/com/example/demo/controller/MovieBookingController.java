package com.example.demo.controller;

import com.example.demo.service.BookingService;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class MovieBookingController {

    private final BookingService bookingService;
    private final PaymentService paymentService;

    @Autowired
    public MovieBookingController(BookingService bookingService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    @PostMapping("/book/{seatNumber}")
    public String bookSeat(@PathVariable String seatNumber,
                           @RequestParam String creditCardNumber,
                           @RequestParam double amount) {

        if (bookingService.isSeatAvailable(seatNumber)) {
            boolean paymentSuccess = paymentService.processPayment(creditCardNumber, amount);

            if (paymentSuccess) {
                bookingService.bookSeat(seatNumber);
                return "Seat booked successfully";
            } else {
                return "Payment failed";
            }
        } else {
            return "Seat not available";
        }
    }

    @GetMapping("/checkAvailability/{seatNumber}")
    public String checkSeatAvailability(@PathVariable String seatNumber) {
        
        if (bookingService.isSeatAvailable(seatNumber)) {
            return "Seat is available";
        } else {
            return "Seat is not available";
        }
    }
}
