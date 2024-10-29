package com.rushan.busreservation.service;

import com.rushan.busreservation.model.AvailabilityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityServiceTest {

  private AvailabilityService availabilityService;

  @BeforeEach
  public void setUp() {
    availabilityService = new AvailabilityService();
  }

  @Test
  public void testCheckAvailabilityValidSegmentEnoughSeats() {
    AvailabilityResponse response = availabilityService.checkAvailability("A", "B", 2);
    assertTrue(response.isAvailable());
    assertEquals(2, response.getAvailableSeats().size());
    assertEquals(100, response.getTotalPrice());
  }

  @Test
  public void testCheckAvailabilityValidSegmentNotEnoughSeats() {
    availabilityService.checkAvailability("A", "B", 40); // Reserve some seats
    AvailabilityResponse response = availabilityService.checkAvailability("A", "B", 2);
    assertTrue(response.isAvailable());
  }

  @Test
  public void testCheckAvailabilityInvalidSegment() {
    assertThrows(IllegalArgumentException.class, () -> {
      availabilityService.checkAvailability("A", "Z", 2);
    });
  }

  @Test
  public void testGetAvailableSeatsMap() {
    Map<String, List<String>> availableSeatsMap = availabilityService.getAvailableSeatsMap();
    assertNotNull(availableSeatsMap);
    assertFalse(availableSeatsMap.isEmpty());
    assertTrue(availableSeatsMap.containsKey("A-B"));
  }
}