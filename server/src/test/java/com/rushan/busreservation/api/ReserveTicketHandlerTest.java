package com.rushan.busreservation.api;

import com.rushan.busreservation.model.Reservation;
import com.rushan.busreservation.model.JourneyInfo;
import com.rushan.busreservation.service.ReservationService;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ReserveTicketHandlerTest {

  private ReserveTicketHandler handler;
  private ReservationService mockReservationService;
  private HttpExchange mockExchange;

  @BeforeEach
  public void setUp() {
    mockReservationService = mock(ReservationService.class);
    handler = new ReserveTicketHandler();
    handler.reservationService = mockReservationService;
    mockExchange = mock(HttpExchange.class);
  }

  @Test
  public void testHandleValidPostRequest() throws IOException {
    String jsonRequest = "{\"origin\":\"A\",\"destination\":\"B\",\"passengerCount\":2,\"amount\":200}";
    InputStream inputStream = new ByteArrayInputStream(jsonRequest.getBytes(StandardCharsets.UTF_8));
    when(mockExchange.getRequestBody()).thenReturn(inputStream);
    when(mockExchange.getRequestMethod()).thenReturn("POST");
    JourneyInfo journeyInfo = new JourneyInfo("A", "B", "2023-10-01", "2023-10-02");
    Reservation mockReservation = new Reservation(1, List.of("1A", "1B"), journeyInfo, 200);
    when(mockReservationService.reserveSeats("A", "B", 2, 200)).thenReturn(mockReservation);
    OutputStream outputStream = new ByteArrayOutputStream();
    when(mockExchange.getResponseBody()).thenReturn(outputStream);
    handler.handle(mockExchange);
    verify(mockExchange).sendResponseHeaders(eq(200), anyLong());
    String jsonResponse = outputStream.toString();
    assertTrue(jsonResponse.contains("\"origin\":\"A\""));
    assertTrue(jsonResponse.contains("\"destination\":\"B\""));
  }

  @Test
  public void testHandleNonPostRequest() throws IOException {
    when(mockExchange.getRequestMethod()).thenReturn("GET");
    handler.handle(mockExchange);
    verify(mockExchange).sendResponseHeaders(eq(405), eq(-1L));
  }
}