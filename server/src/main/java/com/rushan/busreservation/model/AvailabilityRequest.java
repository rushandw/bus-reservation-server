package com.rushan.busreservation.model;

public class AvailabilityRequest
{
  private String origin;
  private String destination;
  private int passengerCount;

  public AvailabilityRequest()
  {
  }

  public AvailabilityRequest(String origin, String destination, int passengerCount)
  {
    this.origin = origin;
    this.destination = destination;
    this.passengerCount = passengerCount;
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

  @Override
  public String toString()
  {
    return "AvailabilityRequest{" + "origin='" + origin + '\'' + ", destination='" + destination + '\'' +
           ", passengerCount=" + passengerCount + '}';
  }
}


