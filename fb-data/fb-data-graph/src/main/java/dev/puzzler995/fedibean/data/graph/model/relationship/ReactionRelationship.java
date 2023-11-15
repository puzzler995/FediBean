package dev.puzzler995.fedibean.data.graph.model.relationship;

import dev.puzzler995.fedibean.data.graph.model.node.UserNode;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@RelationshipProperties
@Data
@AllArgsConstructor
public class ReactionRelationship {
  private final String activityPubId;
  @CreatedDate private final OffsetDateTime createdAt;
  private final String reaction;
  @TargetNode private final UserNode user;
  @RelationshipId
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;
  @Version private Long version;
}
