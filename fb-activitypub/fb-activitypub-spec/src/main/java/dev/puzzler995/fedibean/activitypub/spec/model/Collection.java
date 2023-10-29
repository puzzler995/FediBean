package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.CollectionDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer.CollectionSerializer;
import java.util.List;
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
public class Collection extends APObject {
  private Integer totalItems;
  private Resolvable current;
  private Resolvable first;
  private Resolvable last;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> items;
}
