package com.javasampleapproach.twitterbootstrap.api.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static <T> T toObject(String jsonAsString, Class<T> clazz) {
    try {
      return (T) OBJECT_MAPPER.readValue(jsonAsString, clazz);
    } catch (Exception e) {
      String message = String.format("Error while converting json to : %s", clazz.getSimpleName());
//      log.info(message, e);
      throw new IllegalArgumentException(message, e);
    }
  }

  public static <T> T getObject(String value, Class<T> clazz) throws JsonParseException,
      JsonMappingException, IOException {
    return (T) OBJECT_MAPPER.readValue(value, clazz);
  }

  public static <V> String toJson(V value) throws JsonProcessingException {
    return OBJECT_MAPPER.writeValueAsString(value);
  }

}
