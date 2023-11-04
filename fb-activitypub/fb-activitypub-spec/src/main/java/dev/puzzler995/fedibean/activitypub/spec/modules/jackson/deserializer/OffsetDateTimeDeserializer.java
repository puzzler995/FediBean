package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
  @Override
  public OffsetDateTime deserialize(JsonParser p, DeserializationContext context)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    if (node.isTextual()) {
      String dateStr = node.asText();
      try {
        return OffsetDateTime.parse(dateStr, DateTimeFormatter.ISO_ZONED_DATE_TIME);
      } catch (Exception e) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .atOffset(OffsetDateTime.now().getOffset());
      }
    }
    throw new IllegalArgumentException("Invalid OffsetDateTime format: " + node);
  }
}
