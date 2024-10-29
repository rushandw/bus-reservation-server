package com.rushan.busreservation.model;

public class Seat
{
  private String seatNumber;
  private boolean isAvailable;

  public Seat(String seatNumber, boolean isAvailable)
  {
    this.seatNumber = seatNumber;
    this.isAvailable = isAvailable;
  }

  public String getSeatNumber()
  {
    return seatNumber;
  }

  public void setSeatNumber(String seatNumber)
  {
    this.seatNumber = seatNumber;
  }

  public boolean isAvailable()
  {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable)
  {
    this.isAvailable = isAvailable;
  }
}
