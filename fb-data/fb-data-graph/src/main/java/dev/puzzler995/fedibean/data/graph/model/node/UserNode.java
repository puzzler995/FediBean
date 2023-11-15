package dev.puzzler995.fedibean.data.graph.model.node;

import dev.puzzler995.fedibean.data.graph.model.relationship.FollowRelationship;
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
  private String activityPubId;
  private String displayName;

  @Relationship(type = "FOLLOWS", direction = Direction.OUTGOING)
  private List<FollowRelationship> following;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private String username;
  @Version private Long version;
}
