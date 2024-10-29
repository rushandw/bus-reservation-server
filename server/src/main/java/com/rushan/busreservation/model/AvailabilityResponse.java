package com.rushan.busreservation.model;

import java.util.List;

public class AvailabilityResponse
{
  private boolean available;
  private List<String> availableSeats;
  private int totalPrice;

  public AvailabilityResponse(boolean available, List<String> availableSeats, int totalPrice)
  {
    this.available = available;
    this.availableSeats = availableSeats;
    this.totalPrice = totalPrice;
  }

  public boolean isAvailable()
  {
    return available;
  }

  public void setAvailable(boolean available)
  {
    this.available = available;
  }

  public List<String> getAvailableSeats()
  {
    return availableSeats;
  }

  public void setAvailableSeats(List<String> availableSeats)
  {
    this.availableSeats = availableSeats;
  }

  public int getTotalPrice()
  {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice)
  {
    this.totalPrice = totalPrice;
  }
}
