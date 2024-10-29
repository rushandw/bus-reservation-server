package com.rushan.busreservation.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.rushan.busreservation.model.AvailabilityRequest;
import com.rushan.busreservation.model.AvailabilityResponse;
import com.rushan.busreservation.service.AvailabilityService;
import com.rushan.busreservation.utils.JSONUtility;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CheckAvailabilityHandler implements HttpHandler
{
  AvailabilityService availabilityService = new AvailabilityService();

  @Override
  public void handle(HttpExchange exchange) throws IOException
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

      AvailabilityRequest request = JSONUtility.fromJson(jsonRequest.toString(), AvailabilityRequest.class);
      AvailabilityResponse response = availabilityService.checkAvailability(request.getOrigin(),
                                                                            request.getDestination(),
                                                                            request.getPassengerCount());

      String jsonResponse = JSONUtility.toJson(response);
      exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(jsonResponse.getBytes());
      os.close();
    }
    else
    {
      exchange.sendResponseHeaders(405, -1);
    }
  }
}

