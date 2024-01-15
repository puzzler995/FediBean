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
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node("Password")
@Data
@Builder
public class PasswordEntity {
  @Id
  @GeneratedValue(generatorClass = UUIDGenerator.class)
  private UUID id;

  private String password;

  @Relationship(type = "AUTHENTICATED_BY", direction = Direction.INCOMING)
  private LocalUserEntity user;


  public PasswordEntity withId(UUID id) {
    if (this.id.equals(id)) {
      return this;
    } else {
      return PasswordEntity.builder().id(id).password(this.password).user(this.user).build();
    }
  }
}
