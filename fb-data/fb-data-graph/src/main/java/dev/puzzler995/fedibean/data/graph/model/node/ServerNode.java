package dev.puzzler995.fedibean.data.graph.model.node;

import java.net.URI;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Server")
@Data
@AllArgsConstructor
public class ServerNode {
  private String description;
  private URI faviconUrl;
  private OffsetDateTime firstSeenAt;
  private String host;
  private URI iconUrl;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  private Boolean isBlocked;
  private Boolean isLocal;
  private Boolean isNotResponding;
  private Boolean isSilenced;
  private OffsetDateTime lastRequestReceivedAt;
  private OffsetDateTime lastRequestSentAt;
  private OffsetDateTime lastSeenAt;
  private String maintainer;
  private String maintainerEmail;
  private String name;
  private Integer postsCount;
  private String software;
  private String softwareVersion;
  private String themeColor;
  private Integer usersCount;
  @Version private Long version;
}
