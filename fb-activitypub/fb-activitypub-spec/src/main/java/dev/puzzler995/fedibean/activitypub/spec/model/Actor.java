package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
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
public class Actor extends APObject {
  public static final List<String> types =
      List.of("Application", "Group", "Organization", "Person", "Service");

  @JsonProperty("http://joinmastodon.org/ns#devices")
  private Collection devices;

  @JsonProperty("http://joinmastodon.org/ns#discoverable")
  private Boolean discoverable;

  @JsonProperty("https://www.w3.org/ns/activitystreams#endpoints")
  private Endpoints endpoints;

  @JsonProperty("http://joinmastodon.org/ns#featured")
  private Collection featured;

  @JsonProperty("http://joinmastodon.org/ns#featuredTags")
  private Collection featuredTags;

  @JsonProperty("https://www.w3.org/ns/activitystreams#followers")
  private Collection followers;

  @JsonProperty("https://www.w3.org/ns/activitystreams#following")
  private Collection following;

  @Nonnull
  @JsonProperty("http://www.w3.org/ns/ldp#inbox")
  private Collection inbox =
      new Collection() {
        {
          this.setType("OrderedCollection");
        }
      };

  @JsonProperty("https://misskey-hub.net/ns#isCat")
  private Boolean isCat;

  @JsonProperty("https://www.w3.org/ns/activitystreams#liked")
  private Collection liked;

  @JsonProperty("https://www.w3.org/ns/activitystreams#manuallyApprovesFollowers")
  private Boolean manuallyApprovesFollowers;

  @Nonnull
  @JsonProperty("https://www.w3.org/ns/activitystreams#outbox")
  private Collection outbox =
      new Collection() {
        {
          this.setType("OrderedCollection");
        }
      };

  @JsonProperty("https://www.w3.org/ns/activitystreams#preferredUsername")
  private String preferredUsername;

  @JsonProperty("https://www.w3.org/ns/activitystreams#preferredUsernameMap")
  private ContentMap preferredUsernameMap;

  @JsonProperty("https://w3id.org/security#publicKey")
  private PublicKey publicKey;

  @JsonProperty("https://www.w3.org/ns/activitystreams#sharedInbox")
  private Collection sharedInbox;

  private Collection streams; // todo:???
}
