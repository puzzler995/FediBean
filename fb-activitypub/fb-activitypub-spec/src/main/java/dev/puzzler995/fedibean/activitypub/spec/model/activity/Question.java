package dev.puzzler995.fedibean.activitypub.spec.model.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.OffsetDateTimeDeserializer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Question extends APObject {
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> anyOf = new ArrayList<>();
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime closed;
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> oneOf = new ArrayList<>();
}
