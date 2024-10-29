package com.rushan.busreservation.api;

import com.rushan.busreservation.model.AvailabilityResponse;
import com.rushan.busreservation.service.AvailabilityService;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CheckAvailabilityHandlerTest {

  private CheckAvailabilityHandler handler;
  private AvailabilityService mockService;
  private HttpExchange mockExchange;

  @BeforeEach
  public void setUp() {
    mockService = mock(AvailabilityService.class);
    handler = new CheckAvailabilityHandler();
    handler.availabilityService = mockService;
    mockExchange = mock(HttpExchange.class);
  }

  @Test
  public void testHandleValidPostRequest() throws IOException {
    // Arrange
    String jsonRequest = "{\"origin\":\"A\",\"destination\":\"B\",\"passengerCount\":2}";
    InputStream inputStream = new ByteArrayInputStream(jsonRequest.getBytes(StandardCharsets.UTF_8));
    when(mockExchange.getRequestBody()).thenReturn(inputStream);
    when(mockExchange.getRequestMethod()).thenReturn("POST");

    AvailabilityResponse mockResponse = new AvailabilityResponse(true, List.of("1A", "1B"), 100);
    when(mockService.checkAvailability("A", "B", 2)).thenReturn(mockResponse);

    OutputStream outputStream = new ByteArrayOutputStream();
    when(mockExchange.getResponseBody()).thenReturn(outputStream);

    handler.handle(mockExchange);

    verify(mockExchange).sendResponseHeaders(eq(200), anyLong());
    String jsonResponse = outputStream.toString();
    assertTrue(jsonResponse.contains("\"available\":true"));
    assertTrue(jsonResponse.contains("\"totalPrice\":100"));
  }

  @Test
  public void testHandleNonPostRequest() throws IOException {
    when(mockExchange.getRequestMethod()).thenReturn("GET");
    handler.handle(mockExchange);

    verify(mockExchange).sendResponseHeaders(eq(405), eq(-1L));
  }
}