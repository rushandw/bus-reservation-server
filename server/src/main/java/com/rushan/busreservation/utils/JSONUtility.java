package com.rushan.busreservation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

public class JSONUtility
{
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static <T> T fromJson(String jsonString, Class<T> clazz) throws JSONException
  {
    try
    {
      return objectMapper.readValue(jsonString, clazz);
    }
    catch (Exception e)
    {
      throw new JSONException("Error parsing JSON to object", e);
    }
  }

  public static String toJson(Object object)
  {
    try
    {
      return objectMapper.writeValueAsString(object);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error converting object to JSON", e);
    }
  }
}