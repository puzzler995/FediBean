package old;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.net.URI;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "emoji")
public class Emoji extends DBEntity {
  private static final long serialVersionUID = 8151128860949089553L;
  @Column(name = "category")
  private String category;
  @Column(name = "height")
  private Integer height;
  @ManyToOne
  @JoinColumn(name = "host_id")
  private Server host;
  @Lob
  @Column(name = "license")
  private String license;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "original_url", nullable = false)
  private URL originalUrl;
  @Column(name = "public_url", nullable = false)
  private URL publicUrl;
  @ElementCollection
  @Column(name = "tag")
  @CollectionTable(name = "emoji_tags", joinColumns = @JoinColumn(name = "emoji_id"))
  private List<String> tags = new ArrayList<>();
  @Column(name = "type")
  private String type;
  @LastModifiedDate
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;
  @Column(name = "uri")
  private URI uri;
  @Column(name = "width")
  private Integer width;
}
