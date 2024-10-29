package com.rushan.busreservation.model;

import java.util.List;

public class Reservation
{
  private final int reservationId;
  private final List<String> bookedSeats;
  private final JourneyInfo journeyInfo;
  private final int totalPrice;

  public Reservation(int reservationId, List<String> bookedSeats, JourneyInfo journeyInfo, int totalPrice)
  {
    this.reservationId = reservationId;
    this.bookedSeats = bookedSeats;
    this.journeyInfo = journeyInfo;
    this.totalPrice = totalPrice;
  }

  public int getReservationId()
  {
    return reservationId;
  }

  public List<String> getBookedSeats()
  {
    return bookedSeats;
  }

  public JourneyInfo getJourneyInfo()
  {
    return journeyInfo;
  }

  public int getTotalPrice()
  {
    return totalPrice;
  }
}
