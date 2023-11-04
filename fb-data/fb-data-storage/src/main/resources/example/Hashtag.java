package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
@Table(name = ExampleConstants.HASHTAG_TABLE_NAME)
public class Hashtag implements Serializable {
  private static final long serialVersionUID = 588687169448021158L;

  @Id
  @Column(name = ExampleConstants.HASHTAG_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_NAME_NAME, nullable = false, length = 128)
  private String name;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_MENTIONEDUSERIDS_NAME, nullable = false)
  private List<String> mentionedUserIds;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_MENTIONEDUSERSCOUNT_NAME, nullable = false)
  private Integer mentionedUsersCount;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_MENTIONEDLOCALUSERIDS_NAME, nullable = false)
  private List<String> mentionedLocalUserIds;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_MENTIONEDLOCALUSERSCOUNT_NAME, nullable = false)
  private Integer mentionedLocalUsersCount;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_MENTIONEDREMOTEUSERIDS_NAME, nullable = false)
  private List<String> mentionedRemoteUserIds;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_MENTIONEDREMOTEUSERSCOUNT_NAME, nullable = false)
  private Integer mentionedRemoteUsersCount;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_ATTACHEDUSERIDS_NAME, nullable = false)
  private List<String> attachedUserIds;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_ATTACHEDUSERSCOUNT_NAME, nullable = false)
  private Integer attachedUsersCount;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_ATTACHEDLOCALUSERIDS_NAME, nullable = false)
  private List<String> attachedLocalUserIds;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_ATTACHEDLOCALUSERSCOUNT_NAME, nullable = false)
  private Integer attachedLocalUsersCount;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_ATTACHEDREMOTEUSERIDS_NAME, nullable = false)
  private List<String> attachedRemoteUserIds;

  @Column(name = ExampleConstants.HASHTAG_COLUMN_ATTACHEDREMOTEUSERSCOUNT_NAME, nullable = false)
  private Integer attachedRemoteUsersCount;
}
