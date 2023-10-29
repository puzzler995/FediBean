package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = ExampleConstants.USER_TABLE_NAME)
public class User implements Serializable {
  private static final long serialVersionUID = 7762937024740994434L;

  @Id
  @Column(name = ExampleConstants.USER_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.USER_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = ExampleConstants.USER_COLUMN_UPDATEDAT_NAME)
  private OffsetDateTime updatedAt;

  @Column(name = ExampleConstants.USER_COLUMN_LASTFETCHEDAT_NAME)
  private OffsetDateTime lastFetchedAt;

  @Column(name = ExampleConstants.USER_COLUMN_USERNAME_NAME, nullable = false, length = 128)
  private String username;

  @Column(name = ExampleConstants.USER_COLUMN_USERNAMELOWER_NAME, nullable = false, length = 128)
  private String usernameLower;

  @Column(name = ExampleConstants.USER_COLUMN_NAME_NAME, length = 128)
  private String name;

  @Column(name = ExampleConstants.USER_COLUMN_FOLLOWERSCOUNT_NAME, nullable = false)
  private Integer followersCount;

  @Column(name = ExampleConstants.USER_COLUMN_FOLLOWINGCOUNT_NAME, nullable = false)
  private Integer followingCount;

  @Column(name = ExampleConstants.USER_COLUMN_NOTESCOUNT_NAME, nullable = false)
  private Integer notesCount;

  @OneToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"avatarId\"")
  private DriveFile avatar;

  @OneToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"bannerId\"")
  private DriveFile banner;

  @Column(name = ExampleConstants.USER_COLUMN_TAGS_NAME, nullable = false)
  private List<String> tags;

  @Column(name = ExampleConstants.USER_COLUMN_ISSUSPENDED_NAME, nullable = false)
  private Boolean isSuspended = false;

  @Column(name = ExampleConstants.USER_COLUMN_ISSILENCED_NAME, nullable = false)
  private Boolean isSilenced = false;

  @Column(name = ExampleConstants.USER_COLUMN_ISLOCKED_NAME, nullable = false)
  private Boolean isLocked = false;

  @Column(name = ExampleConstants.USER_COLUMN_ISBOT_NAME, nullable = false)
  private Boolean isBot = false;

  @Column(name = ExampleConstants.USER_COLUMN_ISCAT_NAME, nullable = false)
  private Boolean isCat = false;

  @Column(name = ExampleConstants.USER_COLUMN_ISADMIN_NAME, nullable = false)
  private Boolean isAdmin = false;

  @Column(name = ExampleConstants.USER_COLUMN_ISMODERATOR_NAME, nullable = false)
  private Boolean isModerator = false;

  @Column(name = ExampleConstants.USER_COLUMN_EMOJIS_NAME, nullable = false)
  private List<String> emojis;

  @Column(name = ExampleConstants.USER_COLUMN_HOST_NAME, length = 128)
  private String host;

  @Column(name = ExampleConstants.USER_COLUMN_INBOX_NAME, length = 512)
  private String inbox;

  @Column(name = ExampleConstants.USER_COLUMN_SHAREDINBOX_NAME, length = 512)
  private String sharedInbox;

  @Column(name = ExampleConstants.USER_COLUMN_FEATURED_NAME, length = 512)
  private String featured;

  @Column(name = ExampleConstants.USER_COLUMN_URI_NAME, length = 512)
  private String uri;

  @Column(name = ExampleConstants.USER_COLUMN_TOKEN_NAME, length = 16)
  private String token;

  @Column(name = ExampleConstants.USER_COLUMN_ISEXPLORABLE_NAME, nullable = false)
  private Boolean isExplorable = false;

  @Column(name = ExampleConstants.USER_COLUMN_FOLLOWERSURI_NAME, length = 512)
  private String followersUri;

  @Column(name = ExampleConstants.USER_COLUMN_LASTACTIVEDATE_NAME)
  private OffsetDateTime lastActiveDate;

  @Column(name = ExampleConstants.USER_COLUMN_HIDEONLINESTATUS_NAME, nullable = false)
  private Boolean hideOnlineStatus = false;

  @Column(name = ExampleConstants.USER_COLUMN_ISDELETED_NAME, nullable = false)
  private Boolean isDeleted = false;

  @Column(name = ExampleConstants.USER_COLUMN_DRIVECAPACITYOVERRIDEMB_NAME)
  private Integer driveCapacityOverrideMb;

  @Column(name = ExampleConstants.USER_COLUMN_MOVEDTOURI_NAME, length = 512)
  private String movedToUri;

  @Column(name = ExampleConstants.USER_COLUMN_ALSOKNOWNAS_NAME, length = Integer.MAX_VALUE)
  private String alsoKnownAs;

  @Column(name = ExampleConstants.USER_COLUMN_SPEAKASCAT_NAME, nullable = false)
  private Boolean speakAsCat = false;
}
