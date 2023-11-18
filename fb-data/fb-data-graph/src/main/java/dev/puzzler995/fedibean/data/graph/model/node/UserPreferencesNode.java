package dev.puzzler995.fedibean.data.graph.model.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("UserPreferences")
@Data
@AllArgsConstructor
public class UserPreferencesNode {
  private Boolean autoAcceptFollowers;
  private String email;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private Boolean isEmailConfirmed;
  private Boolean isNoAi;
  private Boolean isNoIndex;
  private Boolean isReactionsPublic;
  private String password;

  @Relationship(type = "PREFERENCES", direction = Direction.INCOMING)
  private UserNode preferencesOf;

  @Version private Long version;
}
