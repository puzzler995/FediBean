package dev.puzzler995.fedibean.data.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.GeneratedValue.UUIDGenerator;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("User")
@Data
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(generatorClass = UUIDGenerator.class)
  private UUID id;

  private String username;

  public UserEntity withId(UUID id) {
    if (this.id.equals(id)) {
      return this;
    } else {
      return UserEntity.builder().id(id).username(this.username).build();
    }
  }
}
