package dev.puzzler995.fedibean.data.graph.model.node;

import java.time.OffsetDateTime;
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

@Node("Announcement")
@Data
@AllArgsConstructor
public class AnnouncementNode {
  private String content;
  private OffsetDateTime createdAt;
  private OffsetDateTime endTime;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private Boolean isActive;

  @Relationship(type = "POSTED", direction = Direction.INCOMING)
  private UserNode postedBy;

  @Relationship(type = "READ", direction = Direction.INCOMING)
  private List<UserNode> readBy;

  private OffsetDateTime startTime;
  private String title;
  private OffsetDateTime updatedAt;
  @Version private Long version;
}
