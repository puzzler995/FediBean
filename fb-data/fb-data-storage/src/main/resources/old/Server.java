package old;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.net.URI;
import java.net.URL;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "server")
public class Server extends DBEntity {
  private static final long serialVersionUID = 1708492892575416936L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "host")
    private URI host;

    @Column(name = "is_local", nullable = false)
    private Boolean isLocal = false;

  @Column(name = "user_count", nullable = false)
  private Integer userCount;

  @Column(name = "note_count", nullable = false)
  private Integer noteCount;

  @Column(name = "following_count", nullable = false)
  private Integer followingCount;

  @Column(name = "followers_count", nullable = false)
  private Integer followersCount;

  @Column(name = "last_request_sent")
  private ZonedDateTime lastRequestSent;

  @Column(name = "latest_status")
  private Integer latestStatus;

  @Column(name = "last_request_receieved")
  private ZonedDateTime lastRequestReceieved;

  @Column(name = "is_not_responding", nullable = false)
  private Boolean isNotResponding = false;

  @Column(name = "software")
  private String software;

  @Column(name = "software_version")
  private String softwareVersion;

  @Column(name = "is_open", nullable = false)
  private Boolean isOpen = false;

  @Lob
  @Column(name = "description")
  private String description;

  @Column(name = "admin_name")
  private String adminName;

  @Column(name = "admin_email")
  private String adminEmail;

  @Column(name = "last_info_updated")
  private ZonedDateTime lastInfoUpdated;

  @Column(name = "is_suspended", nullable = false)
  private Boolean isSuspended = false;

  @Column(name = "icon")
  private URL icon;

  @Column(name = "favicon")
  private URL favicon;

  @Column(name = "theme_color")
  private String themeColor;

}
