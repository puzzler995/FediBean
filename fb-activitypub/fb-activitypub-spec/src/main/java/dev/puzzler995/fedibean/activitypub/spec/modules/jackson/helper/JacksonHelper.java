package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.helper;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.util.List;

public class JacksonHelper {
  public static void writeIfNotNull(JsonGenerator gen, String fieldName, Object value)
      throws IOException {
    if (value != null) {
      gen.writeFieldName(fieldName);
      gen.writeObject(value);
    }
  }

  public static void writeIfNotNull(JsonGenerator gen, String fieldName, List<? extends Object> value)
      throws IOException {
    if (value != null && !value.isEmpty()) {
      gen.writeFieldName(fieldName);
      if (value.size() == 1) {
        gen.writeString(String.valueOf(value.get(0)));
      } else {
        gen.writeStartArray();
        for (Object item : value) {
          gen.writeString(String.valueOf(item));
        }
        gen.writeEndArray();
      }
    }
  }
}
