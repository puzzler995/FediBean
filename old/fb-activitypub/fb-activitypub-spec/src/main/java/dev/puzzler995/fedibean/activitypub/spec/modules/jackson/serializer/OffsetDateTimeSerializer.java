package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

  @Override
  public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException
  {
    gen.writeStartObject();
    gen.writeStringField("@type", "http://www.w3.org/2001/XMLSchema#dateTime");
    gen.writeStringField("@value", value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    gen.writeEndObject();
  }
}
