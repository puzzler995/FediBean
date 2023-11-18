package dev.puzzler995.fedibean.data.graph.model.node;

import dev.puzzler995.fedibean.data.graph.model.relationship.FollowRelationship;
import dev.puzzler995.fedibean.data.graph.model.relationship.MuteRelationship;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("User")
@Data
@AllArgsConstructor
public class UserNode {
  private List<URI> alsoKnownAs;

  @Relationship(type = "AVATAR_FOR", direction = Direction.INCOMING)
  private AssetNode avatar;

  @Relationship(type = "BLOCKS", direction = Direction.OUTGOING)
  private List<UserNode> blocked;

  private OffsetDateTime createdAt;
  private String displayName;
  private URI followersUri;

  @Relationship(type = "FOLLOWS", direction = Direction.OUTGOING)
  private List<FollowRelationship> following;

  @Relationship(type = "HEADER_FOR", direction = Direction.INCOMING)
  private AssetNode header;

  private Boolean hideOnlineStatus;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private URI inbox;
  private Boolean isBot;
  private Boolean isDeleted;
  private Boolean isExplorable;
  private Boolean isLocked;
  private Boolean isSuspended;
  private OffsetDateTime lastActiveAt;
  private OffsetDateTime lastSeenAt;
  private OffsetDateTime movedAt;
  private URI movedTo;

  @Relationship(type = "MUTES", direction = Direction.OUTGOING)
  private List<MuteRelationship> muting;

  @Relationship(type = "ON", direction = Direction.OUTGOING)
  private ServerNode onServer;

  private URI sharedInbox;
  private OffsetDateTime updatedAt;
  private URI uri;
  private String username;
  @Version private Long version;
}
