package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
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
@JsonInclude(Include.NON_EMPTY)
public class Activity extends APObject {
  public static final List<String> types =
      List.of(
          "Accept",
          "Add",
          "Announce",
          "Arrive",
          "Block",
          "Create",
          "Delete",
          "Dislike",
          "Flag",
          "Follow",
          "Ignore",
          "Invite",
          "Join",
          "Leave",
          "Like",
          "Listen",
          "Move",
          "Offer",
          "Reject",
          "Read",
          "Remove",
          "TentativeReject",
          "TentativeAccept",
          "Travel",
          "Undo",
          "Update",
          "View",
          "EmojiReact");

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> actor = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> object = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> target = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> result = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> origin = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> instrument = new ArrayList<>();

  @JsonProperty("_misskey_reaction")
  private String misskeyReaction;

  private Boolean directMessage;

  public Activity addActor(Resolvable actor) {
    this.actor.add(actor);
    return this;
  }

  public Activity addObject(Resolvable object) {
    this.object.add(object);
    return this;
  }

  public Activity addTarget(Resolvable target) {
    this.target.add(target);
    return this;
  }

  public Activity addResult(Resolvable result) {
    this.result.add(result);
    return this;
  }

  public Activity addOrigin(Resolvable origin) {
    this.origin.add(origin);
    return this;
  }

  public Activity addInstrument(Resolvable instrument) {
    this.instrument.add(instrument);
    return this;
  }
}
