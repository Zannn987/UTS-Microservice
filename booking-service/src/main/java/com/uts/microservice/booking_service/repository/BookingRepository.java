package com.uts.microservice.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uts.microservice.booking_service.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

