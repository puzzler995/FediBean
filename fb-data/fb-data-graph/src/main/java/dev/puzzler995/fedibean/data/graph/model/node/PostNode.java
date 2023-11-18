package dev.puzzler995.fedibean.data.graph.model.node;

import dev.puzzler995.fedibean.data.graph.model.relationship.ReactionRelationship;
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

@Node("Post")
@Data
@AllArgsConstructor
public class PostNode {
  @Relationship(type = "ATTACHED_TO", direction = Direction.OUTGOING)
  private List<AssetNode> attachments;

  @Relationship(type = "BOOSTS", direction = Direction.OUTGOING)
  private PostNode boostedPost;

  private String content;
  private OffsetDateTime createdAt;

  @Relationship(type = "EMOJI", direction = Direction.OUTGOING)
  private List<EmojiNode> emojis;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private Boolean isLocalOnly;

  @Relationship(type = "MENTIONS", direction = Direction.OUTGOING)
  private List<UserNode> mentions;

  @Relationship(type = "MUTED", direction = Direction.INCOMING)
  private List<UserNode> mutedBy;

  @Relationship(type = "HAS_POLL", direction = Direction.OUTGOING)
  private PollNode poll;

  @Relationship(type = "POSTED", direction = Direction.INCOMING)
  private UserNode postedBy;

  @Relationship(type = "REACTS_TO", direction = Direction.INCOMING)
  private List<ReactionRelationship> reactions;

  @Relationship(type = "REPLIES_TO", direction = Direction.OUTGOING)
  private PostNode repliesTo;

  private String summary;

  @Relationship(type = "TAGGED_WITH", direction = Direction.OUTGOING)
  private List<HashtagNode> tags;

  private URI uri;
  @Version private Long version;
  private PostVisibility visibility;

  private enum PostVisibility {
    PUBLIC,
    UNLISTED,
    FOLLOWERS,
    DIRECT
  }
}
