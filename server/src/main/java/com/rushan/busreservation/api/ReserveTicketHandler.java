package com.rushan.busreservation.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.rushan.busreservation.model.Reservation;
import com.rushan.busreservation.model.ReservationRequest;
import com.rushan.busreservation.service.AvailabilityService;
import com.rushan.busreservation.service.ReservationService;
import com.rushan.busreservation.utils.JSONUtility;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ReserveTicketHandler implements HttpHandler
{
  ReservationService reservationService = new ReservationService(new AvailabilityService());

  @Override
  public void handle(HttpExchange exchange) throws IOException, UnsupportedEncodingException
  {
    if ("POST".equals(exchange.getRequestMethod()))
    {
      InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
      BufferedReader br = new BufferedReader(isr);
      StringBuilder jsonRequest = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null)
      {
        jsonRequest.append(line);
      }

      ReservationRequest request = JSONUtility.fromJson(jsonRequest.toString(), ReservationRequest.class);
      Reservation reservation = reservationService.reserveSeats(request.getOrigin(),
                                                                request.getDestination(),
                                                                request.getPassengerCount(),
                                                                request.getAmount());

      String jsonResponse = JSONUtility.toJson(reservation);
      exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(jsonResponse.getBytes());
      os.close();
    }
    else
    {
      exchange.sendResponseHeaders(405, -1); // Method not allowed
    }
  }
}

