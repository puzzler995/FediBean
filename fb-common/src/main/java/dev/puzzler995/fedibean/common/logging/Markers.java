package dev.puzzler995.fedibean.common.logging;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class Markers {
  private final Map<String, Object> markers;

  public Markers() {
    markers = new HashMap<>();
  }

  public Markers bookmark(String bookmark) {
    return mark("bookmark", bookmark);
  }

  public Markers data(Object payload) {
    return mark("data", payload);
  }

  public Markers error(Exception error) {
    if (error != null) {
      return mark("error", error.getMessage());
    } else {
      return this;
    }
  }

  public Markers latency(Long startTime) {
    if (startTime != null) {
      return mark("Latency", System.currentTimeMillis() - startTime);
    } else {
      return mark("Latency", "UNKNOWN");
    }
  }

  public Markers mark(String key, Object value) {
    markers.put(key, value);
    return this;
  }

  public Markers statusCode(HttpStatus statusCode) {
    return mark("statusCode", statusCode.value());
  }

  public Markers statusCode(Integer statusCode) {
    return mark("statusCode", statusCode);
  }
}
