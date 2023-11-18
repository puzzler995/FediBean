package dev.puzzler995.fedibean.data.graph.model.node;

import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("AbuseReport")
@Data
@AllArgsConstructor
public class AbuseReportNode {
  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private String reason;

  @Relationship(type = "CREATED_REPORT", direction = Direction.INCOMING)
  private UserNode reportedBy;

  @Relationship(type = "REPORTED_POST", direction = Direction.OUTGOING)
  private PostNode reportedPost;

  @Relationship(type = "REPORTED_USER", direction = Direction.OUTGOING)
  private UserNode reportedUser;

  private URI uri;
  @Version private Long version;
}
