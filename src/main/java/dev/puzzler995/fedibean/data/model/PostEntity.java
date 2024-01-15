package dev.puzzler995.fedibean.data.model;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.GeneratedValue.UUIDGenerator;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node("Post")
@Data
@Builder
public class PostEntity {
  @Id
  @GeneratedValue(UUIDGenerator.class)
  private UUID id;

  private String content;

  @Relationship(type = "POSTED", direction = Direction.INCOMING)
  private UserEntity author;

  private ZonedDateTime createdAt;

  public PostEntity withId(UUID id) {
    if (this.id.equals(id)) {
      return this;
    } else {
      return PostEntity.builder().id(id).content(this.content).author(this.author).createdAt(this.createdAt).build();
    }
  }
}
