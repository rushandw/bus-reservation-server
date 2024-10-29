package com.rushan.busreservation.model;

public class ReservationRequest
{
  private String origin;
  private String destination;
  private int passengerCount;
  private int amount;

  public ReservationRequest()
  {
  }

  public ReservationRequest(String origin, String destination, int passengerCount, int amount)
  {
    this.origin = origin;
    this.destination = destination;
    this.passengerCount = passengerCount;
    this.amount = amount;
  }

  public String getOrigin()
  {
    return origin;
  }

  public void setOrigin(String origin)
  {
    this.origin = origin;
  }

  public String getDestination()
  {
    return destination;
  }

  public void setDestination(String destination)
  {
    this.destination = destination;
  }

  public int getPassengerCount()
  {
    return passengerCount;
  }

  public void setPassengerCount(int passengerCount)
  {
    this.passengerCount = passengerCount;
  }

  public int getAmount()
  {
    return amount;
  }

  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  @Override
  public String toString()
  {
    return "ReservationRequest{" + "origin='" + origin + '\'' + ", destination='" + destination + '\'' +
           ", passengerCount=" + passengerCount + ", amount=" + amount + '}';
  }
}


