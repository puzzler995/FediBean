package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.CollectionDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer.CollectionSerializer;
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
@JsonInclude(Include.NON_EMPTY)
@JsonDeserialize(using = CollectionDeserializer.class)
@JsonSerialize(using = CollectionSerializer.class)
public class CollectionPage extends Collection {
  private Resolvable next;
  private Resolvable partOf;
  private Resolvable prev;
  private Integer startIndex;
}
