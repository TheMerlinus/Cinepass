package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private Map<String, Boolean> inMemorySeats;

    @Autowired
    public BookingService(BookingRepository bookingRepository, int totalSeats) {
        this.bookingRepository = bookingRepository;
        this.inMemorySeats = initializeInMemorySeats(totalSeats);
    }

    private Map<String, Boolean> initializeInMemorySeats(int totalSeats) {
        Map<String, Boolean> seats = new HashMap<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.put(String.valueOf(i), false);
        }
        return seats;
    }

    public synchronized boolean bookSeat(String seatNumber) {
        if (inMemorySeats.containsKey(seatNumber) && !inMemorySeats.get(seatNumber)) {
            inMemorySeats.put(seatNumber, true);

            // Save the booking information to the database
            Booking booking = new Booking();
            booking.setSeatNumber(seatNumber);
            bookingRepository.save(booking);

            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean isSeatAvailable(String seatNumber) {
        return inMemorySeats.containsKey(seatNumber) && !inMemorySeats.get(seatNumber);
    }

    // Additional methods for accessing booking information from the database
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}

