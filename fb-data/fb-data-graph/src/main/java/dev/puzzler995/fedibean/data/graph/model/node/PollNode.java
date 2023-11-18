package dev.puzzler995.fedibean.data.graph.model.node;

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

@Node("Poll")
@Data
@AllArgsConstructor
public class PollNode {
  private Boolean allowsMultiple;
  private OffsetDateTime expiresAt;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  @Relationship(type = "HAS_OPTIONS", direction = Direction.OUTGOING)
  private List<PollOptionNode> options;

  @Version private Long version;
}
