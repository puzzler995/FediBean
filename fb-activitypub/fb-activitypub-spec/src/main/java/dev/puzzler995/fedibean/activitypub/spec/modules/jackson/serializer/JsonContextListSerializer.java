package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.puzzler995.fedibean.activitypub.spec.model.JsonContext;
import jakarta.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

//@JsonComponent
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
    if (value == null || value.isEmpty()) {
      return;
    }
    if (value.size() == 1) {
      gen.writeString(value.get(0).toString());
    } else {
      gen.writeStartArray();
      for (JsonContext context : value) {
        gen.writeObject(context);
      }
      gen.writeEndArray();
    }
  }
}
