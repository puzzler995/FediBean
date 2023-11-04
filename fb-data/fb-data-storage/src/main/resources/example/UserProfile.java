package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

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
@Table(name = ExampleConstants.USERPROFILE_TABLE_NAME)
public class UserProfile implements Serializable {
  private static final long serialVersionUID = -3629392654160855342L;

  @Id
  @Column(name = ExampleConstants.USERPROFILE_COLUMN_USERID_NAME, nullable = false, length = 32)
  private String userId;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_LOCATION_NAME, length = 128)
  private String location;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_BIRTHDAY_NAME, length = 10)
  private String birthday;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_DESCRIPTION_NAME, length = 2048)
  private String description;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_FIELDS_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> fields;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_URL_NAME, length = 512)
  private String url;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_EMAIL_NAME, length = 128)
  private String email;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_EMAILVERIFYCODE_NAME, length = 128)
  private String emailVerifyCode;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_EMAILVERIFIED_NAME, nullable = false)
  private Boolean emailVerified = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_TWOFACTORTEMPSECRET_NAME, length = 128)
  private String twoFactorTempSecret;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_TWOFACTORSECRET_NAME, length = 128)
  private String twoFactorSecret;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_TWOFACTORENABLED_NAME, nullable = false)
  private Boolean twoFactorEnabled = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_PASSWORD_NAME, length = 128)
  private String password;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_CLIENTDATA_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> clientData;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_AUTOACCEPTFOLLOWED_NAME, nullable = false)
  private Boolean autoAcceptFollowed = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_ALWAYSMARKNSFW_NAME, nullable = false)
  private Boolean alwaysMarkNsfw = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_CAREFULBOT_NAME, nullable = false)
  private Boolean carefulBot = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_USERHOST_NAME, length = 128)
  private String userHost;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_SECURITYKEYSAVAILABLE_NAME, nullable = false)
  private Boolean securityKeysAvailable = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_USEPASSWORDLESSLOGIN_NAME, nullable = false)
  private Boolean usePasswordLessLogin = false;

  @OneToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"pinnedPageId\"")
  private Page pinnedPage;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_ROOM_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> room;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_INTEGRATIONS_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> integrations;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_INJECTFEATUREDNOTE_NAME, nullable = false)
  private Boolean injectFeaturedNote = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_ENABLEWORDMUTE_NAME, nullable = false)
  private Boolean enableWordMute = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_MUTEDWORDS_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> mutedWords;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_NOCRAWLE_NAME, nullable = false)
  private Boolean noCrawle = false;

  @Column(
      name = ExampleConstants.USERPROFILE_COLUMN_RECEIVEANNOUNCEMENTEMAIL_NAME,
      nullable = false)
  private Boolean receiveAnnouncementEmail = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_EMAILNOTIFICATIONTYPES_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> emailNotificationTypes;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_LANG_NAME, length = 32)
  private String lang;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_MUTEDINSTANCES_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> mutedInstances;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_PUBLICREACTIONS_NAME, nullable = false)
  private Boolean publicReactions = false;

  @Column(
      name = ExampleConstants.USERPROFILE_COLUMN_MODERATIONNOTE_NAME,
      nullable = false,
      length = 8192)
  private String moderationNote;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_AUTOSENSITIVE_NAME, nullable = false)
  private Boolean autoSensitive = false;

  @Column(name = ExampleConstants.USERPROFILE_COLUMN_PREVENTAILEARNING_NAME, nullable = false)
  private Boolean preventAiLearning = false;

  /*
      TODO [JPA Buddy] create field to map the '\"mutingNotificationTypes\"' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.USERPROFILE_COLUMN_MUTINGNOTIFICATIONTYPES_NAME, columnDefinition = "user_profile_mutingnotificationtypes_enum[](0, 0) not null")private Object mutingNotificationTypes;
  */
  /*
      TODO [JPA Buddy] create field to map the '\"ffVisibility\"' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.USERPROFILE_COLUMN_FFVISIBILITY_NAME, columnDefinition = "user_profile_ffvisibility_enum(0, 0) not null")private Object ffVisibility;
  */
}
