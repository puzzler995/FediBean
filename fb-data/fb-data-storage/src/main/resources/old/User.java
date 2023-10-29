package old;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
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
@Table(name = "user")
public class User extends DBEntity implements Serializable {
  @Serial private static final long serialVersionUID = -2805912190570544290L;

  @Column(name = "username", nullable = false)
  private String username;

  @LastModifiedDate
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "last_fetched_at")
  private ZonedDateTime lastFetchedAt;

  @Column(name = "preferred_username")
  private String preferredUsername;

  @Column(name = "followers_count", nullable = false)
  private Integer followersCount;

  @Column(name = "following_count", nullable = false)
  private String followingCount;

  @Column(name = "notes_count", nullable = false)
  private Integer notesCount;

  @ElementCollection
  @Column(name = "tag", nullable = false)
  @CollectionTable(name = "user_tags", joinColumns = @JoinColumn(name = "owner_id"))
  private List<String> tags = new ArrayList<>();

  @Column(name = "is_suspended", nullable = false)
  private Boolean isSuspended = false;

  @Column(name = "is_silenced", nullable = false)
  private Boolean isSilenced = false;

  @Column(name = "is_locked", nullable = false)
  private Boolean isLocked = false;

  @Column(name = "is_bot", nullable = false)
  private Boolean isBot = false;

  @Column(name = "is_admin", nullable = false)
  private Boolean isAdmin = false;

  @Column(name = "is_mod", nullable = false)
  private Boolean isMod = false;



  @Column(name = "inbox")
  private URI inbox;

  @Column(name = "shared_inbox")
  private URI sharedInbox;

  @Column(name = "featured")
  private String featured;

  @Column(name = "uri")
  private URI uri;

  @Column(name = "token")
  private String token; // TODO: dafuck is this?

  @Column(name = "is_explorable", nullable = false)
  private Boolean isExplorable = false; // TODO: dafuck is this?

  @Column(name = "followers_uri")
  private URI followersUri;

  @Column(name = "last_active_date")
  private ZonedDateTime lastActiveDate;

  @Column(name = "hide_online_status", nullable = false)
  private Boolean hideOnlineStatus = false;

  @Column(name = "is_deleted", nullable = false)
  private Boolean isDeleted = false;

  @Column(name = "moved_to")
  private URI movedTo;

  @Column(name = "is_indexable", nullable = false)
  private Boolean isIndexable = false;

  @ManyToOne
  @JoinColumn(name = "avatar_id")
  private Asset avatar;

  @ManyToOne
  @JoinColumn(name = "header_id")
  private Asset header;

  @ManyToOne
  @JoinColumn(name = "host_id")
  private Server host;

}
