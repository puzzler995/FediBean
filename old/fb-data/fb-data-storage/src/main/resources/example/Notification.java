package example;

import dev.puzzler995.fedibean.data.model.FollowRequest;
import dev.puzzler995.fedibean.data.model.Note;
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
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
@Table(name = ExampleConstants.NOTIFICATION_TABLE_NAME)
public class Notification implements Serializable {
  private static final long serialVersionUID = -1173773624033566783L;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"appAccessTokenId\"")
  private AccessToken appAccessToken;
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_CHOICE_NAME)
  private Integer choice;
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_CUSTOMBODY_NAME, length = 2048)
  private String customBody;
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_CUSTOMHEADER_NAME, length = 256)
  private String customHeader;
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_CUSTOMICON_NAME, length = 1024)
  private String customIcon;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"followRequestId\"")
  private FollowRequest followRequest;
  @Id
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_ISREAD_NAME, nullable = false)
  private Boolean isRead = false;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"noteId\"")
  private Note note;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"notifieeId\"", nullable = false)
  private User notifiee;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"notifierId\"")
  private User notifier;
  @Column(name = ExampleConstants.NOTIFICATION_COLUMN_REACTION_NAME, length = 128)
  private String reaction;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userGroupInvitationId\"")
  private UserGroupInvitation userGroupInvitation;

  /*
      TODO [JPA Buddy] create field to map the 'type' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.NOTIFICATION_COLUMN_TYPE_NAME, columnDefinition = "notification_type_enum(0, 0) not null")private Object type;
  */
}
