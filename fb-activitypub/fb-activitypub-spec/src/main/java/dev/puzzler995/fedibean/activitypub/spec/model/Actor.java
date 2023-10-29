package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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

  @Nonnull
  private Collection inbox =
      new Collection() {
        {
          this.setType("OrderedCollection");
        }
      };

  @Nonnull
  private Collection outbox =
      new Collection() {
        {
          this.setType("OrderedCollection");
        }
      };

  private Collection following;
  private Collection followers;
  private Collection liked;
  private Collection streams;
  private Collection featured;
  private Collection featuredTags;
  private String preferredUsername;
  private ContentMap preferredUsernameMap;
  private Boolean manuallyApprovesFollowers;
  private Boolean discoverable;
  private Collection devices;
  private PublicKey publicKey;
  private Endpoints endpoints;
  private Collection sharedInbox;
  private Boolean isCat;
}
