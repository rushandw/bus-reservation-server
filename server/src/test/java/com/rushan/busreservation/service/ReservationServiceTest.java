package com.rushan.busreservation.service;

import com.rushan.busreservation.model.AvailabilityResponse;
import com.rushan.busreservation.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

  private ReservationService reservationService;
  private AvailabilityService mockAvailabilityService;

  @BeforeEach
  public void setUp() {
    mockAvailabilityService = mock(AvailabilityService.class);
    reservationService = new ReservationService(mockAvailabilityService);
  }

  @Test
  public void testReserveSeatsInsufficientSeats() {
    String origin = "A";
    String destination = "B";
    int passengerCount = 2;
    int paymentAmount = 100;
    AvailabilityResponse mockResponse = new AvailabilityResponse(false, List.of(), 0);
    when(mockAvailabilityService.checkAvailability(origin, destination, passengerCount)).thenReturn(mockResponse);
    Reservation reservation = reservationService.reserveSeats(origin, destination, passengerCount, paymentAmount);
    assertNull(reservation);
  }

  @Test
  public void testReserveSeatsIncorrectPaymentAmount() {
    // Arrange
    String origin = "A";
    String destination = "B";
    int passengerCount = 2;
    int paymentAmount = 50;
    List<String> availableSeats = List.of("1A", "1B");
    AvailabilityResponse mockResponse = new AvailabilityResponse(true, availableSeats, 100);
    when(mockAvailabilityService.checkAvailability(origin, destination, passengerCount)).thenReturn(mockResponse);

    Reservation reservation = reservationService.reserveSeats(origin, destination, passengerCount, paymentAmount);

    assertNull(reservation);
  }
}