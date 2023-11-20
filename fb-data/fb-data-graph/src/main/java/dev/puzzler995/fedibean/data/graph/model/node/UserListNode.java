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

@Node("UserList")
@Data
@AllArgsConstructor
public class UserListNode {
  @Relationship(type = "CREATED", direction = Direction.INCOMING)
  private UserNode createdBy;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private Boolean isPublic;

  @Relationship(type = "MEMBER_OF", direction = Direction.INCOMING)
  private List<UserNode> members;

  private String name;
  @Version private Long version;
}