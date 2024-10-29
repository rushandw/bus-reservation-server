package com.rushan.busreservation.model;

public class JourneyInfo
{
  private final String origin;
  private final String destination;
  private final String departureTime;
  private final String arrivalTime;

  public JourneyInfo(String origin, String destination, String departureTime, String arrivalTime)
  {
    this.origin = origin;
    this.destination = destination;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
  }

  public String getOrigin()
  {
    return origin;
  }

  public String getDestination()
  {
    return destination;
  }

  public String getDepartureTime()
  {
    return departureTime;
  }

  public String getArrivalTime()
  {
    return arrivalTime;
  }
}
