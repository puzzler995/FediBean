package dev.puzzler995.fedibean.data.graph.model.node;

import java.net.URI;
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

@Node("Emoji")
@Data
@AllArgsConstructor
public class EmojiNode {
  private String category;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  @Relationship(type = "IMAGE_FOR", direction = Direction.INCOMING)
  private AssetNode image;

  private List<String> keywords;
  private String license;
  private String name;

  @Relationship(type = "ON", direction = Direction.OUTGOING)
  private ServerNode onServer;

  private URI source;
  @Version private Long version;
}
