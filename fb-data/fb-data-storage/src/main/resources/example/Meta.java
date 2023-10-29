package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
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
@Table(name = ExampleConstants.META_TABLE_NAME)
public class Meta implements Serializable {
  private static final long serialVersionUID = -2532669445009902094L;

  @Id
  @Column(name = ExampleConstants.META_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.META_COLUMN_NAME_NAME, length = 128)
  private String name;

  @Column(name = ExampleConstants.META_COLUMN_DESCRIPTION_NAME, length = 1024)
  private String description;

  @Column(name = ExampleConstants.META_COLUMN_MAINTAINERNAME_NAME, length = 128)
  private String maintainerName;

  @Column(name = ExampleConstants.META_COLUMN_MAINTAINEREMAIL_NAME, length = 128)
  private String maintainerEmail;

  @Column(name = ExampleConstants.META_COLUMN_DISABLEREGISTRATION_NAME, nullable = false)
  private Boolean disableRegistration = false;

  @Column(name = ExampleConstants.META_COLUMN_DISABLELOCALTIMELINE_NAME, nullable = false)
  private Boolean disableLocalTimeline = false;

  @Column(name = ExampleConstants.META_COLUMN_DISABLEGLOBALTIMELINE_NAME, nullable = false)
  private Boolean disableGlobalTimeline = false;

  @Column(name = ExampleConstants.META_COLUMN_USESTARFORREACTIONFALLBACK_NAME, nullable = false)
  private Boolean useStarForReactionFallback = false;

  @Column(name = ExampleConstants.META_COLUMN_LANGS_NAME, nullable = false)
  private List<String> langs;

  @Column(name = ExampleConstants.META_COLUMN_HIDDENTAGS_NAME, nullable = false)
  private List<String> hiddenTags;

  @Column(name = ExampleConstants.META_COLUMN_BLOCKEDHOSTS_NAME, nullable = false)
  private List<String> blockedHosts;

  @Column(name = ExampleConstants.META_COLUMN_MASCOTIMAGEURL_NAME, length = 512)
  private String mascotImageUrl;

  @Column(name = ExampleConstants.META_COLUMN_BANNERURL_NAME, length = 512)
  private String bannerUrl;

  @Column(name = ExampleConstants.META_COLUMN_ERRORIMAGEURL_NAME, length = 512)
  private String errorImageUrl;

  @Column(name = ExampleConstants.META_COLUMN_ICONURL_NAME, length = 512)
  private String iconUrl;

  @Column(name = ExampleConstants.META_COLUMN_CACHEREMOTEFILES_NAME, nullable = false)
  private Boolean cacheRemoteFiles = false;

  @Column(name = ExampleConstants.META_COLUMN_ENABLERECAPTCHA_NAME, nullable = false)
  private Boolean enableRecaptcha = false;

  @Column(name = ExampleConstants.META_COLUMN_RECAPTCHASITEKEY_NAME, length = 64)
  private String recaptchaSiteKey;

  @Column(name = ExampleConstants.META_COLUMN_RECAPTCHASECRETKEY_NAME, length = 64)
  private String recaptchaSecretKey;

  @Column(name = ExampleConstants.META_COLUMN_LOCALDRIVECAPACITYMB_NAME, nullable = false)
  private Integer localDriveCapacityMb;

  @Column(name = ExampleConstants.META_COLUMN_REMOTEDRIVECAPACITYMB_NAME, nullable = false)
  private Integer remoteDriveCapacityMb;

  @Column(name = ExampleConstants.META_COLUMN_SUMMALYPROXY_NAME, length = 128)
  private String summalyProxy;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEEMAIL_NAME, nullable = false)
  private Boolean enableEmail = false;

  @Column(name = ExampleConstants.META_COLUMN_EMAIL_NAME, length = 128)
  private String email;

  @Column(name = ExampleConstants.META_COLUMN_SMTPSECURE_NAME, nullable = false)
  private Boolean smtpSecure = false;

  @Column(name = ExampleConstants.META_COLUMN_SMTPHOST_NAME, length = 128)
  private String smtpHost;

  @Column(name = ExampleConstants.META_COLUMN_SMTPPORT_NAME)
  private Integer smtpPort;

  @Column(name = ExampleConstants.META_COLUMN_SMTPUSER_NAME, length = 1024)
  private String smtpUser;

  @Column(name = ExampleConstants.META_COLUMN_SMTPPASS_NAME, length = 1024)
  private String smtpPass;

  @Column(name = ExampleConstants.META_COLUMN_ENABLESERVICEWORKER_NAME, nullable = false)
  private Boolean enableServiceWorker = false;

  @Column(name = ExampleConstants.META_COLUMN_SWPUBLICKEY_NAME, length = 128)
  private String swPublicKey;

  @Column(name = ExampleConstants.META_COLUMN_SWPRIVATEKEY_NAME, length = 128)
  private String swPrivateKey;

  @Column(name = ExampleConstants.META_COLUMN_ENABLETWITTERINTEGRATION_NAME, nullable = false)
  private Boolean enableTwitterIntegration = false;

  @Column(name = ExampleConstants.META_COLUMN_TWITTERCONSUMERKEY_NAME, length = 128)
  private String twitterConsumerKey;

  @Column(name = ExampleConstants.META_COLUMN_TWITTERCONSUMERSECRET_NAME, length = 128)
  private String twitterConsumerSecret;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEGITHUBINTEGRATION_NAME, nullable = false)
  private Boolean enableGithubIntegration = false;

  @Column(name = ExampleConstants.META_COLUMN_GITHUBCLIENTID_NAME, length = 128)
  private String githubClientId;

  @Column(name = ExampleConstants.META_COLUMN_GITHUBCLIENTSECRET_NAME, length = 128)
  private String githubClientSecret;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEDISCORDINTEGRATION_NAME, nullable = false)
  private Boolean enableDiscordIntegration = false;

  @Column(name = ExampleConstants.META_COLUMN_DISCORDCLIENTID_NAME, length = 128)
  private String discordClientId;

  @Column(name = ExampleConstants.META_COLUMN_DISCORDCLIENTSECRET_NAME, length = 128)
  private String discordClientSecret;

  @Column(name = ExampleConstants.META_COLUMN_PINNEDUSERS_NAME, nullable = false)
  private List<String> pinnedUsers;

  @Column(name = ExampleConstants.META_COLUMN_TOSURL_NAME, length = 512)
  private String toSUrl;

  @Column(name = ExampleConstants.META_COLUMN_REPOSITORYURL_NAME, nullable = false, length = 512)
  private String repositoryUrl;

  @Column(name = ExampleConstants.META_COLUMN_FEEDBACKURL_NAME, length = 512)
  private String feedbackUrl;

  @Column(name = ExampleConstants.META_COLUMN_USEOBJECTSTORAGE_NAME, nullable = false)
  private Boolean useObjectStorage = false;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEBUCKET_NAME, length = 512)
  private String objectStorageBucket;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEPREFIX_NAME, length = 512)
  private String objectStoragePrefix;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEBASEURL_NAME, length = 512)
  private String objectStorageBaseUrl;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEENDPOINT_NAME, length = 512)
  private String objectStorageEndpoint;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEREGION_NAME, length = 512)
  private String objectStorageRegion;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEACCESSKEY_NAME, length = 512)
  private String objectStorageAccessKey;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGESECRETKEY_NAME, length = 512)
  private String objectStorageSecretKey;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEPORT_NAME)
  private Integer objectStoragePort;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEUSESSL_NAME, nullable = false)
  private Boolean objectStorageUseSSL = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"proxyAccountId\"")
  private User proxyAccount;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGEUSEPROXY_NAME, nullable = false)
  private Boolean objectStorageUseProxy = false;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEHCAPTCHA_NAME, nullable = false)
  private Boolean enableHcaptcha = false;

  @Column(name = ExampleConstants.META_COLUMN_HCAPTCHASITEKEY_NAME, length = 64)
  private String hcaptchaSiteKey;

  @Column(name = ExampleConstants.META_COLUMN_HCAPTCHASECRETKEY_NAME, length = 64)
  private String hcaptchaSecretKey;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGESETPUBLICREAD_NAME, nullable = false)
  private Boolean objectStorageSetPublicRead = false;

  @Column(name = ExampleConstants.META_COLUMN_PINNEDPAGES_NAME, nullable = false)
  private List<String> pinnedPages;

  @Column(name = ExampleConstants.META_COLUMN_BACKGROUNDIMAGEURL_NAME, length = 512)
  private String backgroundImageUrl;

  @Column(name = ExampleConstants.META_COLUMN_LOGOIMAGEURL_NAME, length = 512)
  private String logoImageUrl;

  @Column(name = ExampleConstants.META_COLUMN_PINNEDCLIPID_NAME, length = 32)
  private String pinnedClipId;

  @Column(name = ExampleConstants.META_COLUMN_OBJECTSTORAGES3FORCEPATHSTYLE_NAME, nullable = false)
  private Boolean objectStorageS3ForcePathStyle = false;

  @Column(name = ExampleConstants.META_COLUMN_ALLOWEDHOSTS_NAME)
  private List<String> allowedHosts;

  @Column(name = ExampleConstants.META_COLUMN_SECUREMODE_NAME)
  private Boolean secureMode;

  @Column(name = ExampleConstants.META_COLUMN_PRIVATEMODE_NAME)
  private Boolean privateMode;

  @Column(name = ExampleConstants.META_COLUMN_DEEPLAUTHKEY_NAME, length = 128)
  private String deeplAuthKey;

  @Column(name = ExampleConstants.META_COLUMN_DEEPLISPRO_NAME, nullable = false)
  private Boolean deeplIsPro = false;

  @Column(name = ExampleConstants.META_COLUMN_EMAILREQUIREDFORSIGNUP_NAME, nullable = false)
  private Boolean emailRequiredForSignup = false;

  @Column(name = ExampleConstants.META_COLUMN_THEMECOLOR_NAME, length = 512)
  private String themeColor;

  @Column(name = ExampleConstants.META_COLUMN_DEFAULTLIGHTTHEME_NAME, length = 8192)
  private String defaultLightTheme;

  @Column(name = ExampleConstants.META_COLUMN_DEFAULTDARKTHEME_NAME, length = 8192)
  private String defaultDarkTheme;

  @Column(name = ExampleConstants.META_COLUMN_SETSENSITIVEFLAGAUTOMATICALLY_NAME, nullable = false)
  private Boolean setSensitiveFlagAutomatically = false;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEIPLOGGING_NAME, nullable = false)
  private Boolean enableIpLogging = false;

  @Column(
      name = ExampleConstants.META_COLUMN_ENABLESENSITIVEMEDIADETECTIONFORVIDEOS_NAME,
      nullable = false)
  private Boolean enableSensitiveMediaDetectionForVideos = false;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEACTIVEEMAILVALIDATION_NAME, nullable = false)
  private Boolean enableActiveEmailValidation = false;

  @Column(name = ExampleConstants.META_COLUMN_CUSTOMMOTD_NAME, nullable = false)
  private List<String> customMOTD;

  @Column(name = ExampleConstants.META_COLUMN_CUSTOMSPLASHICONS_NAME, nullable = false)
  private List<String> customSplashIcons;

  @Column(name = ExampleConstants.META_COLUMN_DISABLERECOMMENDEDTIMELINE_NAME, nullable = false)
  private Boolean disableRecommendedTimeline = false;

  @Column(name = ExampleConstants.META_COLUMN_RECOMMENDEDINSTANCES_NAME, nullable = false)
  private List<String> recommendedInstances;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEGUESTTIMELINE_NAME, nullable = false)
  private Boolean enableGuestTimeline = false;

  @Column(name = ExampleConstants.META_COLUMN_DEFAULTREACTION_NAME, nullable = false, length = 256)
  private String defaultReaction;

  @Column(name = ExampleConstants.META_COLUMN_LIBRETRANSLATEAPIURL_NAME, length = 512)
  private String libreTranslateApiUrl;

  @Column(name = ExampleConstants.META_COLUMN_LIBRETRANSLATEAPIKEY_NAME, length = 128)
  private String libreTranslateApiKey;

  @Column(name = ExampleConstants.META_COLUMN_SILENCEDHOSTS_NAME, nullable = false)
  private List<String> silencedHosts;

  @Column(name = ExampleConstants.META_COLUMN_EXPERIMENTALFEATURES_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> experimentalFeatures;

  @Column(name = ExampleConstants.META_COLUMN_ENABLEIDENTICONGENERATION_NAME, nullable = false)
  private Boolean enableIdenticonGeneration = false;

  @Column(name = ExampleConstants.META_COLUMN_ENABLESERVERMACHINESTATS_NAME, nullable = false)
  private Boolean enableServerMachineStats = false;

  @Column(name = ExampleConstants.META_COLUMN_DONATIONLINK_NAME, length = 256)
  private String donationLink;

  /*
      TODO [JPA Buddy] create field to map the '\"sensitiveMediaDetection\"' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.META_COLUMN_SENSITIVEMEDIADETECTION_NAME, columnDefinition = "meta_sensitivemediadetection_enum(0, 0) not null")private Object sensitiveMediaDetection;
  */
  /*
      TODO [JPA Buddy] create field to map the '\"sensitiveMediaDetectionSensitivity\"' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.META_COLUMN_SENSITIVEMEDIADETECTIONSENSITIVITY_NAME, columnDefinition = "meta_sensitivemediadetectionsensitivity_enum(0, 0) not null")private Object sensitiveMediaDetectionSensitivity;
  */
}
