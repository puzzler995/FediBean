package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import dev.puzzler995.fedibean.data.model.embeds.AssetProperty;
import dev.puzzler995.fedibean.data.model.embeds.RequestHeader;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.ASSET_TABLE_NAME)
public class Asset extends DBItem implements Serializable {
  @Serial private static final long serialVersionUID = -3447416019399923146L;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "owner_id")
  private User owner;

  @Column(name = DBConstant.ASSET_COLUMN_MD5_NAME, nullable = false)
  private String md5;

  @Column(name = DBConstant.ASSET_COLUMN_NAME_NAME, nullable = false)
  private String name;

  @Column(name = DBConstant.ASSET_COLUMN_TYPE_NAME, nullable = false)
  private String type;

  @Column(name = DBConstant.ASSET_COLUMN_SIZE_NAME, nullable = false)
  private Integer size;

  @Lob
  @Column(name = DBConstant.ASSET_COLUMN_COMMENT_NAME)
  private String comment;

  @Column(name = DBConstant.ASSET_COLUMN_STOREDINTERNAL_NAME, nullable = false)
  private Boolean storedInternal = false;

  @Column(name = DBConstant.ASSET_COLUMN_URL_NAME, nullable = false, length = 512)
  private URL url;

  @Column(name = DBConstant.ASSET_COLUMN_THUMBNAILURL_NAME, length = 512)
  private URL thumbnailUrl;

  @Column(name = DBConstant.ASSET_COLUMN_WEBPUBLICURL_NAME, length = 512)
  private URL webPublicUrl;

  @Column(name = DBConstant.ASSET_COLUMN_ACCESSKEY_NAME)
  private String accessKey;

  @Column(name = DBConstant.ASSET_COLUMN_THUMBNAILACCESSKEY_NAME)
  private String thumbnailAccessKey;

  @Column(name = DBConstant.ASSET_COLUMN_WEBPUBLICACCESSKEY_NAME)
  private String webpublicAccessKey;

  @Column(name = DBConstant.ASSET_COLUMN_URI_NAME, length = 512)
  private URI uri;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "folder_id")
  private AssetFolder folder;

  @Column(name = DBConstant.ASSET_COLUMN_ISSENSITIVE_NAME, nullable = false)
  private Boolean isSensitive = false;

  @Column(name = DBConstant.ASSET_COLUMN_ISLINK_NAME, nullable = false)
  private Boolean isLink = false;

  @Column(name = DBConstant.ASSET_COLUMN_BLURHASH_NAME)
  private String blurhash;

  @Column(name = DBConstant.ASSET_COLUMN_WEBPUBLICTYPE_NAME)
  private String webPublicType;

  @Column(name = DBConstant.ASSET_COLUMN_REQUESTIP_NAME)
  private String requestIp;

  @ElementCollection
  @CollectionTable(name = "asset_assetProperty", joinColumns = @JoinColumn(name = "owner_id"))
  private List<AssetProperty> assetProperties = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "asset_requestHeaders", joinColumns = @JoinColumn(name = "owner_id"))
  private List<RequestHeader> requestHeaders = new ArrayList<>();

  // TODO: Maybe porn maybe sensitive?

}
