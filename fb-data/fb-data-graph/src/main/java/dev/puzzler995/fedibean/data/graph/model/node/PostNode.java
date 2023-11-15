package dev.puzzler995.fedibean.data.graph.model.node;

import dev.puzzler995.fedibean.data.graph.model.relationship.ReactionRelationship;
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
  private String activityPubId;

  @Relationship(type = "BOOSTS", direction = Direction.OUTGOING)
  private PostNode boostedPost;

  private String content;
  private OffsetDateTime createdAt;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  @Relationship(type = "POSTED_BY", direction = Direction.OUTGOING)
  private UserNode postedBy;
  @Relationship(type = "REACTS_TO", direction = Direction.INCOMING)
  private List<ReactionRelationship> reactions;
  @Relationship(type = "REPLIES_TO", direction = Direction.OUTGOING)
  private PostNode repliesTo;
  @Version private Long version;
}
