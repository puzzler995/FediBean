package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.USER_TABLE_NAME)
public class User extends DBItem implements Serializable {
  @Serial private static final long serialVersionUID = 682752665201874058L;

  @LastModifiedDate
  @Column(name = DBConstant.USER_COLUMN_UPDATEDAT_NAME)
  private OffsetDateTime updatedAt;

  @Column(name = DBConstant.USER_COLUMN_LASTFETCHEDAT_NAME)
  private OffsetDateTime lastFetchedAt;

  @Column(name = DBConstant.USER_COLUMN_USERNAME_NAME, nullable = false)
  private String username;

  @Column(name = DBConstant.USER_COLUMN_NAME_NAME)
  private String name;

  @Column(name = DBConstant.USER_COLUMN_FOLLOWERSCOUNT_NAME, nullable = false)
  private Integer followersCount;

  @Column(name = DBConstant.USER_COLUMN_FOLLOWINGCOUNT_NAME, nullable = false)
  private Integer followingCount;

  @Column(name = DBConstant.USER_COLUMN_NOTESCOUNT_NAME, nullable = false)
  private Integer notesCount;

  @ElementCollection
  @Column(name = DBConstant.USER_COLUMN_TAGS_NAME, nullable = false)
  @CollectionTable(name = "user_tags", joinColumns = @JoinColumn(name = "owner_id"))
  private Set<String> tags = new LinkedHashSet<>();

  @Column(name = DBConstant.USER_COLUMN_ISSUSPENDED_NAME, nullable = false)
  private Boolean isSuspended = false;

  @Column(name = DBConstant.USER_COLUMN_ISSILENCED_NAME, nullable = false)
  private Boolean isSilenced = false;

  @Column(name = DBConstant.USER_COLUMN_ISLOCKED_NAME, nullable = false)
  private Boolean isLocked = false;

  @Column(name = DBConstant.USER_COLUMN_ISBOT_NAME, nullable = false)
  private Boolean isBot = false;

  @Column(name = DBConstant.USER_COLUMN_ISADMIN_NAME, nullable = false)
  private Boolean isAdmin = false;

  @Column(name = DBConstant.USER_COLUMN_ISMOD_NAME, nullable = false)
  private Boolean isMod = false;

  @ElementCollection
  @Column(name = DBConstant.USER_COLUMN_EMOJI_NAME, nullable = false)
  @CollectionTable(name = "user_emoji", joinColumns = @JoinColumn(name = "owner_id"))
  private Set<String> emoji = new LinkedHashSet<>(); // TODO: What is this?

  @Column(name = DBConstant.USER_COLUMN_HOST_NAME)
  private URI host;

  @Column(name = DBConstant.USER_COLUMN_INBOX_NAME, length = 512)
  private URI inbox;

  @Column(name = DBConstant.USER_COLUMN_SHAREDINBOX_NAME, length = 512)
  private URI sharedInbox;

  @Column(name = DBConstant.USER_COLUMN_FEATURED_NAME, length = 512)
  private URI featured;

  @Column(name = DBConstant.USER_COLUMN_URI_NAME, length = 512)
  private URI uri;

  @Column(name = DBConstant.USER_COLUMN_TOKEN_NAME)
  private String token; // TODO: What is this?

  @Column(name = DBConstant.USER_COLUMN_ISEXPLORABLE_NAME, nullable = false)
  private Boolean isExplorable = false;

  @Column(name = DBConstant.USER_COLUMN_FOLLOWERSURI_NAME, length = 512)
  private URI followersUri;

  @Column(name = DBConstant.USER_COLUMN_LASTACTIVEDATE_NAME)
  private OffsetDateTime lastActiveDate;

  @Column(name = DBConstant.USER_COLUMN_HIDEONLINESTATUS_NAME, nullable = false)
  private Boolean hideOnlineStatus = false;

  @Column(name = DBConstant.USER_COLUMN_ISDELETED_NAME, nullable = false)
  private Boolean isDeleted = false;

  @Column(name = DBConstant.USER_COLUMN_MOVEDTO_NAME, length = 512)
  private URI movedTo;

  @Lob
  @Column(name = DBConstant.USER_COLUMN_ALSOKNOWNAS_NAME)
  private String alsoKnownAs;

  @Column(name = DBConstant.USER_COLUMN_AVATAR_NAME)
  private URI avatar;

  // TODO: Add Banner
  // TODO: Decide on Misskey Cat Support
  // TODO: Decide on Drive Capacity info

  @Transient
  public String getLowercaseUsername() {
    if (username != null) {
      return username.toLowerCase();
    }
    return null;
  }
}
