package dev.puzzler995.fedibean.data.graph.model.dto;

import dev.puzzler995.fedibean.data.graph.model.node.EmojiNode;
import dev.puzzler995.fedibean.data.graph.model.relationship.FollowRelationship;
import dev.puzzler995.fedibean.data.graph.model.relationship.MuteRelationship;
import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO for {@link dev.puzzler995.fedibean.data.graph.model.node.UserNode} and {@link
 * dev.puzzler995.fedibean.data.graph.model.node.UserProfileNode}
 */
@Getter
@Builder
public class UserDto implements Serializable {
  @Serial private static final long serialVersionUID = 653993869389211056L;
  private List<URI> alsoKnownAs;
  private String avatarAverageColor;
  private String avatarBlurhash;
  private URI avatarUrl;
  private String bio;
  private LocalDate birthday;
  private OffsetDateTime createdAt;
  private String displayName;
  private List<EmojiNode> emojisInBio;
  private Map<String, String> fields;
  private URI followersUri;
  private List<FollowRelationship> following;
  private String headerAverageColor;
  private String headerBlurhash;
  private URI headerUrl;
  private Boolean hideOnlineStatus;
  private String id;
  private URI inbox;
  private Boolean isBot;
  private Boolean isDeleted;
  private Boolean isExplorable;
  private Boolean isLocked;
  private Boolean isSuspended;
  private OffsetDateTime lastActiveAt;
  private OffsetDateTime lastSeenAt;
  private String location;
  private OffsetDateTime movedAt;
  private URI movedTo;
  private List<MuteRelationship> muting;
  private String onServerHost;
  private String pronouns;
  private URI sharedInbox;
  private OffsetDateTime updatedAt;
  private URI uri;
  private String username;
  private List<URI> verifiedLinks;
}
