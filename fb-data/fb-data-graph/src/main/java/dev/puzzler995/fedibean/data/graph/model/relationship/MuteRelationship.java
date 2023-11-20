package dev.puzzler995.fedibean.data.graph.model.relationship;

import dev.puzzler995.fedibean.data.graph.model.node.UserNode;
import java.net.URI;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@RelationshipProperties
@Data
@AllArgsConstructor
public class MuteRelationship {
  private final OffsetDateTime createdAt;
  private final URI uri;
  private OffsetDateTime expiresAt;

  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  @RelationshipId @GeneratedValue private Long internalId;
  @TargetNode private UserNode user;
  @Version private Long version;
}