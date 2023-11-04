package old;

import dev.puzzler995.fedibean.data.model.AssetFolder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.net.URI;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "asset")
public class Asset extends DBEntity implements Serializable {
  private static final long serialVersionUID = -8119767871290391598L;

  @ManyToOne
  @JoinColumn(name = "created_by_id")
  private User createdBy;

  @Column(name = "md5", nullable = false)
  private String md5;

  @Column(name = "file_name", nullable = false)
  private String fileName;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "size", nullable = false)
  private Integer size;

  @Column(name = "comment")
  private String comment;

  @Column(name = "url", nullable = false)
  private URI url;

  @Column(name = "thumbnail_url")
  private URI thumbnailUrl;

  @Column(name = "is_remote", nullable = false)
  private Boolean isRemote = false;

  @Column(name = "uri")
  private URI uri;

  @Column(name = "source")
  private String source;

  @ManyToOne
  @JoinColumn(name = "asset_folder_id")
  private AssetFolder assetFolder;

  @Column(name = "is_sensitive", nullable = false)
  private Boolean isSensitive = false;

  @Column(name = "is_link", nullable = false)
  private Boolean isLink = false;

  @Column(name = "blur_hash")
  private String blurHash;
}
