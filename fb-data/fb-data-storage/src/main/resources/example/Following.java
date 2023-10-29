package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
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
@Table(name = ExampleConstants.FOLLOWING_TABLE_NAME)
public class Following implements Serializable {
  private static final long serialVersionUID = -2622535670219145710L;

  @Id
  @Column(name = ExampleConstants.FOLLOWING_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.FOLLOWING_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"followeeId\"", nullable = false)
  private User followee;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"followerId\"", nullable = false)
  private User follower;

  @Column(name = ExampleConstants.FOLLOWING_COLUMN_FOLLOWERHOST_NAME, length = 128)
  private String followerHost;

  @Column(name = ExampleConstants.FOLLOWING_COLUMN_FOLLOWERINBOX_NAME, length = 512)
  private String followerInbox;

  @Column(name = ExampleConstants.FOLLOWING_COLUMN_FOLLOWERSHAREDINBOX_NAME, length = 512)
  private String followerSharedInbox;

  @Column(name = ExampleConstants.FOLLOWING_COLUMN_FOLLOWEEHOST_NAME, length = 128)
  private String followeeHost;

  @Column(name = ExampleConstants.FOLLOWING_COLUMN_FOLLOWEEINBOX_NAME, length = 512)
  private String followeeInbox;

  @Column(name = ExampleConstants.FOLLOWING_COLUMN_FOLLOWEESHAREDINBOX_NAME, length = 512)
  private String followeeSharedInbox;
}
