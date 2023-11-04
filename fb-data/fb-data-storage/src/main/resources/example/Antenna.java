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
@Table(name = ExampleConstants.ANTENNA_TABLE_NAME)
public class Antenna implements Serializable {
  private static final long serialVersionUID = 3085393926227766350L;

  @Id
  @Column(name = ExampleConstants.ANTENNA_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_NAME_NAME, nullable = false, length = 128)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userListId\"")
  private UserList userList;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_KEYWORDS_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> keywords;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_WITHFILE_NAME, nullable = false)
  private Boolean withFile = false;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_EXPRESSION_NAME, length = 2048)
  private String expression;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_NOTIFY_NAME, nullable = false)
  private Boolean notify = false;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_CASESENSITIVE_NAME, nullable = false)
  private Boolean caseSensitive = false;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_WITHREPLIES_NAME, nullable = false)
  private Boolean withReplies = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userGroupJoiningId\"")
  private UserGroupJoining userGroupJoining;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_USERS_NAME, nullable = false)
  private List<String> users;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_EXCLUDEKEYWORDS_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> excludeKeywords;

  @Column(name = ExampleConstants.ANTENNA_COLUMN_INSTANCES_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> instances;

  /*
      TODO [JPA Buddy] create field to map the 'src' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.ANTENNA_COLUMN_SRC_NAME, columnDefinition = "antenna_src_enum(0, 0) not null")private Object src;
  */
}
