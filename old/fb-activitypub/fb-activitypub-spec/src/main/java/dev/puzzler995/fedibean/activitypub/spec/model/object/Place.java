package dev.puzzler995.fedibean.activitypub.spec.model.object;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Place extends APObject {
  private Float accuracy;
  private Float altitude;
  private Float latitude;
  private Float longitude;
  private Float radius;
  private String units; // TODO: Make handle URIs correctly
}
