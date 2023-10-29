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
@Table(name = ExampleConstants.NOTE_TABLE_NAME)
public class Note implements Serializable {
  private static final long serialVersionUID = -389272998040172612L;

  @Id
  @Column(name = ExampleConstants.NOTE_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.NOTE_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"replyId\"")
  private Note reply;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"renoteId\"")
  private Note renote;

  @Column(name = ExampleConstants.NOTE_COLUMN_TEXT_NAME, length = Integer.MAX_VALUE)
  private String text;

  @Column(name = ExampleConstants.NOTE_COLUMN_NAME_NAME, length = 256)
  private String name;

  @Column(name = ExampleConstants.NOTE_COLUMN_CW_NAME, length = 512)
  private String cw;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(name = ExampleConstants.NOTE_COLUMN_LOCALONLY_NAME, nullable = false)
  private Boolean localOnly = false;

  @Column(name = ExampleConstants.NOTE_COLUMN_RENOTECOUNT_NAME, nullable = false)
  private Short renoteCount;

  @Column(name = ExampleConstants.NOTE_COLUMN_REPLIESCOUNT_NAME, nullable = false)
  private Short repliesCount;

  @Column(name = ExampleConstants.NOTE_COLUMN_REACTIONS_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> reactions;

  @Column(name = ExampleConstants.NOTE_COLUMN_URI_NAME, length = 512)
  private String uri;

  @Column(name = ExampleConstants.NOTE_COLUMN_SCORE_NAME, nullable = false)
  private Integer score;

  @Column(name = ExampleConstants.NOTE_COLUMN_FILEIDS_NAME, nullable = false)
  private List<String> fileIds;

  @Column(name = ExampleConstants.NOTE_COLUMN_ATTACHEDFILETYPES_NAME, nullable = false)
  private List<String> attachedFileTypes;

  @Column(name = ExampleConstants.NOTE_COLUMN_VISIBLEUSERIDS_NAME, nullable = false)
  private List<String> visibleUserIds;

  @Column(name = ExampleConstants.NOTE_COLUMN_MENTIONS_NAME, nullable = false)
  private List<String> mentions;

  @Column(
      name = ExampleConstants.NOTE_COLUMN_MENTIONEDREMOTEUSERS_NAME,
      nullable = false,
      length = Integer.MAX_VALUE)
  private String mentionedRemoteUsers;

  @Column(name = ExampleConstants.NOTE_COLUMN_EMOJIS_NAME, nullable = false)
  private List<String> emojis;

  @Column(name = ExampleConstants.NOTE_COLUMN_TAGS_NAME, nullable = false)
  private List<String> tags;

  @Column(name = ExampleConstants.NOTE_COLUMN_HASPOLL_NAME, nullable = false)
  private Boolean hasPoll = false;

  @Column(name = ExampleConstants.NOTE_COLUMN_USERHOST_NAME, length = 128)
  private String userHost;

  @Column(name = ExampleConstants.NOTE_COLUMN_REPLYUSERID_NAME, length = 32)
  private String replyUserId;

  @Column(name = ExampleConstants.NOTE_COLUMN_REPLYUSERHOST_NAME, length = 128)
  private String replyUserHost;

  @Column(name = ExampleConstants.NOTE_COLUMN_RENOTEUSERID_NAME, length = 32)
  private String renoteUserId;

  @Column(name = ExampleConstants.NOTE_COLUMN_RENOTEUSERHOST_NAME, length = 128)
  private String renoteUserHost;

  @Column(name = ExampleConstants.NOTE_COLUMN_URL_NAME, length = 512)
  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"channelId\"")
  private Channel channel;

  @Column(name = ExampleConstants.NOTE_COLUMN_THREADID_NAME, length = 256)
  private String threadId;

  @Column(name = ExampleConstants.NOTE_COLUMN_UPDATEDAT_NAME)
  private OffsetDateTime updatedAt;

  /*
      TODO [JPA Buddy] create field to map the 'visibility' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.NOTE_COLUMN_VISIBILITY_NAME, columnDefinition = "note_visibility_enum(0, 0) not null")private Object visibility;
  */
}
