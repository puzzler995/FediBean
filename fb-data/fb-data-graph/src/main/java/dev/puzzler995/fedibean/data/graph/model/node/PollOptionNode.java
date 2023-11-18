package dev.puzzler995.fedibean.data.graph.model.node;

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

@Node("PollOption")
@Data
@AllArgsConstructor
public class PollOptionNode {
  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private String text;
  @Version private Long version;

  @Relationship(type = "VOTED", direction = Direction.INCOMING)
  private List<UserNode> votedBy;

  private Integer votesCount;
}
