package dev.puzzler995.fedibean.data.graph.model.node;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("UserProfile")
@Data
@AllArgsConstructor
public class UserProfileNode {
  private String bio;
  private Date birthday;

  @Relationship(type = "IN_BIO", direction = Direction.INCOMING)
  private List<EmojiNode> emojisInBio;

  @CompositeProperty(prefix = "field_")
  private Map<String, String> fields;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private String location;
  private String pronouns;

  @Relationship(type = "PROFILE", direction = Direction.INCOMING)
  private UserNode user;

  private List<URI> verifiedLinks;
  @Version private Long version;
}
