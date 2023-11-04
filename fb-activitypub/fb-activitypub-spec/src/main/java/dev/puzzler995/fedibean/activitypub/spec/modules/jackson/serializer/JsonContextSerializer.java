package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.puzzler995.fedibean.activitypub.spec.model.JsonContext;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class JsonContextSerializer extends JsonSerializer<JsonContext> {
  @Override
  public void serialize(JsonContext context, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeString(context.toString());
    //        if (contexts == null || contexts.isEmpty()) {
    //        } else if (contexts.size() == 1) {
    //            gen.writeString(contexts.get(0).toString());
    //        } else if (contexts.size() > 1) {
    //            gen.writeStartArray();
    //            for (JsonContext context : contexts) {
    //                gen.writeString(context.toString());
    //            }
    //            gen.writeEndArray();
    //        }
  }
}
