package com.rushan.busreservation.service;

import java.util.*;

import com.rushan.busreservation.model.AvailabilityResponse;

public class AvailabilityService
{

  private static final Map<String, Integer> ticketPrices = new HashMap<>();

  static
  {
    ticketPrices.put("A-B", 50);
    ticketPrices.put("A-C", 100);
    ticketPrices.put("A-D", 150);
    ticketPrices.put("B-C", 50);
    ticketPrices.put("B-D", 100);
    ticketPrices.put("C-D", 50);
  }

  private static final Map<String, List<String>> availableSeats = new HashMap<>();

  static
  {
    for (String segment : ticketPrices.keySet())
    {
      List<String> seats = new ArrayList<>();
      for (int i = 1; i <= 10; i++)
      {  // 10 rows
        seats.add(i + "A");
        seats.add(i + "B");
        seats.add(i + "C");
        seats.add(i + "D");
      }
      availableSeats.put(segment, seats);
    }
  }

  /**
   * Check availability of seats and calculate total price for the journey.
   *
   * @param origin         The starting point of the journey.
   * @param destination    The endpoint of the journey.
   * @param passengerCount The number of passengers.
   * @return AvailabilityResponse object with available seats and total price.
   */
  public AvailabilityResponse checkAvailability(String origin, String destination, int passengerCount)
  {
    String segmentKey = origin + "-" + destination;

    if (!ticketPrices.containsKey(segmentKey))
    {
      throw new IllegalArgumentException("Invalid journey segment.");
    }

    List<String> seats = availableSeats.get(segmentKey);

    if (seats.size() < passengerCount)
    {
      return new AvailabilityResponse(false, Collections.emptyList(), 0);
    }

    int pricePerTicket = ticketPrices.get(segmentKey);
    int totalPrice = pricePerTicket * passengerCount;

    List<String> allocatedSeats = seats.subList(0, passengerCount);

    return new AvailabilityResponse(true, new ArrayList<>(allocatedSeats), totalPrice);
  }

  public Map<String, List<String>> getAvailableSeatsMap()
  {
    return availableSeats;
  }
}