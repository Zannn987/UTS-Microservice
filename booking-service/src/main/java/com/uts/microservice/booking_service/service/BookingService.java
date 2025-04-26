package com.uts.microservice.booking_service.service;

import com.uts.microservice.booking_service.model.Booking;
import com.uts.microservice.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Ambil semua booking
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Ambil booking berdasarkan ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Membuat booking baru
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Update booking berdasarkan ID
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking tidak ditemukan dengan id " + id));

        booking.setCustomerId(bookingDetails.getCustomerId());
        booking.setMovieId(bookingDetails.getMovieId());
        booking.setBookingTime(bookingDetails.getBookingTime());
        booking.setNumberOfTickets(bookingDetails.getNumberOfTickets());

        return bookingRepository.save(booking);
    }

    // Menghapus booking berdasarkan ID
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking tidak ditemukan dengan id " + id));
        bookingRepository.delete(booking);
    }
}
