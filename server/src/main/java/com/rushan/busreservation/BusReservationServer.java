package com.rushan.busreservation;

import com.rushan.busreservation.api.CheckAvailabilityHandler;
import com.rushan.busreservation.api.ReserveTicketHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.InetSocketAddress;

public class BusReservationServer
{
  public static void main(String[] args) throws IOException
  {
    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    server.createContext("/api/check-availability", new CheckAvailabilityHandler());
    server.createContext("/api/reserve-ticket", (HttpHandler) new ReserveTicketHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
    System.out.println("Server started at http://localhost:8080");
  }
}

