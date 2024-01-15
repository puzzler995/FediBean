package dev.puzzler995.fedibean.data.model;

import java.util.List;
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

@Node("LocalUser")
@Data
@Builder
public class LocalUserEntity {
  @Id
  @GeneratedValue(generatorClass = UUIDGenerator.class)
  private UUID id;

  @Relationship(type = "REGISTERED_AS", direction = Direction.INCOMING)
  private UserEntity user;

  private boolean isEnabled;
  private boolean isAccountNonExpired;
  private boolean isCredentialsNonExpired;
  private boolean isAccountNonLocked;

  @Relationship(type = "HAS_ROLE", direction = Direction.OUTGOING)
  private List<RoleEntity> roles;

  private AUTHENTICATION_TYPE authenticationType;

  public LocalUserEntity withId(UUID id) {
    if (this.id.equals(id)) {
      return this;
    } else {
      return LocalUserEntity.builder().id(id).user(this.user).isEnabled(this.isEnabled).isAccountNonExpired(this.isAccountNonExpired).isCredentialsNonExpired(this.isCredentialsNonExpired).isAccountNonLocked(this.isAccountNonLocked).roles(this.roles).authenticationType(this.authenticationType).build();
    }
  }

  public enum AUTHENTICATION_TYPE {
    PASSWORD,
    REMOTE
  }
}
