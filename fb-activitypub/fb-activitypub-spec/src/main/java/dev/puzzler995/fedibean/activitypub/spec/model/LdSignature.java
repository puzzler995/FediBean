package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.OffsetDateTimeDeserializer;
import java.net.URI;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class LdSignature {
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime created;
  private URI creator;
  private String signatureValue;
  private String type;
}
