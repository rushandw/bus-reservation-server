package com.rushan.busreservation.service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.rushan.busreservation.model.AvailabilityResponse;
import com.rushan.busreservation.model.JourneyInfo;
import com.rushan.busreservation.model.Reservation;

public class ReservationService
{
  private static final AtomicInteger reservationCounter = new AtomicInteger(1);

  private final Map<Integer, Reservation> reservations = new ConcurrentHashMap<>();

  private final AvailabilityService availabilityService;

  public ReservationService(AvailabilityService availabilityService)
  {
    this.availabilityService = availabilityService;
  }

  /**
   * Reserves seats for the journey if available and returns a Reservation object.
   *
   * @param origin         The starting point of the journey.
   * @param destination    The endpoint of the journey.
   * @param passengerCount The number of passengers.
   * @param paymentAmount  The payment amount (should match calculated price).
   * @return Reservation object containing reservation details, or null if reservation fails.
   */
  public Reservation reserveSeats(String origin, String destination, int passengerCount, int paymentAmount)
  {
    AvailabilityResponse availabilityResponse =
      availabilityService.checkAvailability(origin, destination, passengerCount);

    if (!availabilityResponse.isAvailable())
    {
      System.out.println("Insufficient seats available.");
      return null;
    }

    int totalPrice = availabilityResponse.getTotalPrice();
    if (paymentAmount != totalPrice)
    {
      System.out.println("Payment amount does not match the required price.");
      return null;
    }

    int reservationId = reservationCounter.getAndIncrement();
    List<String> bookedSeats = availabilityResponse.getAvailableSeats();

    String segmentKey = origin + "-" + destination;
    synchronized (availabilityService)
    {
      List<String> seats = availabilityService.getAvailableSeatsMap().get(segmentKey);
      seats.removeAll(bookedSeats);
    }

    JourneyInfo journeyInfo = new JourneyInfo(origin, destination, "08:00", "10:00"); // Fixed times for simplicity

    Reservation reservation = new Reservation(reservationId, bookedSeats, journeyInfo, totalPrice);
    reservations.put(reservationId, reservation);

    return reservation;
  }

  /**
   * Retrieves a reservation by ID.
   *
   * @param reservationId The unique reservation ID.
   * @return Reservation object or null if not found.
   */
  public Reservation getReservation(int reservationId)
  {
    return reservations.get(reservationId);
  }
}

