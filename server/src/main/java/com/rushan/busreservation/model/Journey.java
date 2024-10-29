package com.rushan.busreservation.model;

public class Journey
{
  private String origin;
  private String destination;
  private int price;

  public Journey(String origin, String destination, int price)
  {
    this.origin = origin;
    this.destination = destination;
    this.price = price;
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

  public int getPrice()
  {
    return price;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }
}
