package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.net.URI;
import java.net.URL;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.SERVER_TABLE_NAME)
public class Server extends DBItem {
  private static final long serialVersionUID = 480271932775938342L;
  @Column(name = DBConstant.SERVER_COLUMN_ADMINEMAIL_NAME)
  private String adminEmail;
  @Column(name = DBConstant.SERVER_COLUMN_ADMINNAME_NAME)
  private String adminName;
  @Lob
  @Column(name = DBConstant.SERVER_COLUMN_DESCRIPTION_NAME)
  private String description;
  @Column(name = DBConstant.SERVER_COLUMN_FAVICONURL_NAME)
  private URL faviconUrl;
  @Column(name = DBConstant.SERVER_COLUMN_FOLLOWERSCOUNT_NAME, nullable = false)
  private Integer followersCount;
  @Column(name = DBConstant.SERVER_COLUMN_FOLLOWINGCOUNT_NAME, nullable = false)
  private Integer followingCount;
  @Column(name = DBConstant.SERVER_COLUMN_HOST_NAME, nullable = false)
  private URI host;
  @Column(name = DBConstant.SERVER_COLUMN_ICONURL_NAME)
  private URL iconUrl;
  @Column(name = DBConstant.SERVER_COLUMN_ISOPEN_NAME)
  private Boolean isOpen;
  @Column(name = DBConstant.SERVER_COLUMN_ISSUSPENDED_NAME, nullable = false)
  private Boolean isSuspended = false;
  @Column(name = DBConstant.SERVER_COLUMN_LASTCOMMUNICATED_NAME, nullable = false)
  private OffsetDateTime lastCommunicated;
  @Column(name = DBConstant.SERVER_COLUMN_LASTINFOUPDATE_NAME)
  private OffsetDateTime lastInfoUpdate;
  @Column(name = DBConstant.SERVER_COLUMN_LASTREQUESTRECEIVED_NAME)
  private OffsetDateTime lastRequestReceived;
  @Column(name = DBConstant.SERVER_COLUMN_LASTREQUESTSENT_NAME)
  private OffsetDateTime lastRequestSent;
  @Column(name = DBConstant.SERVER_COLUMN_LATESTSTATUS_NAME)
  private Integer latestStatus;
  @Column(name = DBConstant.SERVER_COLUMN_NAME_NAME)
  private String name;
  @Column(name = DBConstant.SERVER_COLUMN_NOTECOUNT_NAME, nullable = false)
  private Integer noteCount;
  @Column(name = DBConstant.SERVER_COLUMN_SOFTWARENAME_NAME)
  private String softwareName;
  @Column(name = DBConstant.SERVER_COLUMN_SOFTWAREVERSION_NAME)
  private String softwareVersion;
  @Column(name = DBConstant.SERVER_COLUMN_THEMECOLOR_NAME)
  private String themeColor;
  @Column(name = DBConstant.SERVER_COLUMN_USERCOUNT_NAME, nullable = false)
  private Integer userCount;
}
