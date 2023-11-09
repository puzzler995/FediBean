package dev.puzzler995.fedibean.activitypub.spec.model.object;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import dev.puzzler995.fedibean.activitypub.spec.model.CompactedIri;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Relationship extends APObject {
  private Resolvable object;
  private Resolvable subject;

  @JsonGetter("relationship")
  public CompactedIri getRelationship() {
    return this.getId();
  }

  @JsonSetter("relationship")
  public Relationship setRelationship(CompactedIri relationship) {
    this.setId(relationship);
    return this;
  }
}
