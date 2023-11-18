package dev.puzzler995.fedibean.data.graph.model.node;

import java.net.URI;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Asset")
@Data
@AllArgsConstructor
public class AssetNode {
  private String averageColor;
  private String blurhash;
  private String comment;
  private OffsetDateTime createdAt;
  private Integer duration;
  private Integer height;

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String id;

  @Relationship(type = "IN", direction = Direction.OUTGOING)
  private AssetFolderNode inFolder;

  private Boolean isLocal;
  private Boolean isSensitive;
  private String md5;
  private String mimeType;
  private String name;
  private Integer orientation;
  private URI publicUrl;
  private Integer size;
  private URI thumbnailUrl;

  @Relationship(type = "UPLOADED", direction = Direction.INCOMING)
  private UserNode uploadedBy;

  private URI uri;
  private URI url;
  @Version private Long version;
  private Integer width;
}
