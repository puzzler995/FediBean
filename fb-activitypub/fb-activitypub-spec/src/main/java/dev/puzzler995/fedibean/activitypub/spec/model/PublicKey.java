package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class PublicKey {
  @JsonProperty("@id")
  private URI id;
  @JsonProperty("https://w3id.org/security#owner")
  private Resolvable owner;
  @JsonProperty("https://w3id.org/security#publicKeyPem")
  private String publicKeyPem;
  @JsonProperty("@type")
  private String type;
}
