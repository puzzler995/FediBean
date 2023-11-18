package dev.puzzler995.fedibean.data.graph.model.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Hashtag")
@Data
@AllArgsConstructor
public class HashtagNode {
  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private String name;
  @Version private Long version;
}
