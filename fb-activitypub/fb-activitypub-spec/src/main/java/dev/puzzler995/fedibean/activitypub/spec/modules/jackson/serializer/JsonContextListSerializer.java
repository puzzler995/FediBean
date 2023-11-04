package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.puzzler995.fedibean.activitypub.spec.model.JsonContext;
import jakarta.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class JsonContextListSerializer extends StdSerializer<List<JsonContext>> {

  public JsonContextListSerializer() {
    this(List.class);
  }

  @SuppressWarnings("unchecked")
  public JsonContextListSerializer(@Nonnull Class<?> t) {
    super((Class<List<JsonContext>>) t);
  }

  @Override
  public void serialize(List<JsonContext> value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    List<String> stringValues =
        value.stream()
            .filter(jsonContext -> jsonContext.getPrefix() == null)
            .map(JsonContext::toString)
            .toList();
    Map<String, String> mapValues =
        value.stream()
            .filter(jsonContext -> jsonContext.getPrefix() != null)
            .collect(
                java.util.stream.Collectors.toMap(
                    JsonContext::getPrefix, JsonContext::getSuffix, (a, b) -> b));

    if (stringValues.size() == 1 && mapValues.isEmpty()) {
      gen.writeString(stringValues.get(0));
    } else if (stringValues.isEmpty()) {
      gen.writeStartObject();
      for (Map.Entry<String, String> entry : mapValues.entrySet()) {
        gen.writeStringField(entry.getKey(), entry.getValue());
      }
      gen.writeEndObject();
    } else {
      gen.writeStartArray();
      for (String stringValue : stringValues) {
        gen.writeString(stringValue);
      }
      if (!mapValues.isEmpty()) {
        gen.writeObject(mapValues);
      }
      gen.writeEndArray();
    }
  }
}
