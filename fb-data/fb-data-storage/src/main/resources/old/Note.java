package old;

import dev.puzzler995.fedibean.data.model.Asset;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "note")
public class Note extends DBEntity implements Serializable {
  private static final long serialVersionUID = -3705102227463321172L;

  @ManyToOne
  @JoinColumn(name = "reply_to_id")
  private Note replyTo;

  @Column(name = "name")
  private String name;

  @Lob
  @Column(name = "content_warning")
  private String contentWarning;

  @ManyToOne(optional = false)
  @JoinColumn(name = "creator_id", nullable = false)
  private User creator;

  @Column(name = "local_only", nullable = false)
  private Boolean localOnly = false;

  @Column(name = "boost_count", nullable = false)
  private Integer boostCount;

  @Column(name = "reply_count", nullable = false)
  private Integer replyCount;

  @Column(name = "uri")
  private URI uri;

  @Lob
  @Column(name = "text")
  private String text;

  @ManyToMany
  @JoinTable(
      name = "note_attachments",
      joinColumns = @JoinColumn(name = "note_id"),
      inverseJoinColumns = @JoinColumn(name = "attachments_id"))
  private Set<Asset> attachments = new LinkedHashSet<>();
}
