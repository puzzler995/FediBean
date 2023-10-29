package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.puzzler995.fedibean.activitypub.spec.model.CompactedIri;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CompactedIriSerializer extends JsonSerializer<CompactedIri> {
  @Override
  public void serialize(CompactedIri value, JsonGenerator generator, SerializerProvider serializers)
      throws IOException {
    generator.writeString(value.toString());
  }
}
