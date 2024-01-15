package dev.puzzler995.fedibean.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Role")
@Data
@Builder
public class RoleEntity {
  @Id private String name;
}
