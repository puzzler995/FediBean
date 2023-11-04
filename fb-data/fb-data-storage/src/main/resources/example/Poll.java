package example;

import dev.puzzler995.fedibean.data.model.Note;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
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
@Table(name = ExampleConstants.POLL_TABLE_NAME)
public class Poll implements Serializable {
  private static final long serialVersionUID = 9112047729195168914L;

  @Id
  @Column(name = ExampleConstants.POLL_COLUMN_NOTEID_NAME, nullable = false, length = 32)
  private String noteId;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"noteId\"", nullable = false)
  private Note note;

  @Column(name = ExampleConstants.POLL_COLUMN_EXPIRESAT_NAME)
  private OffsetDateTime expiresAt;

  @Column(name = ExampleConstants.POLL_COLUMN_MULTIPLE_NAME, nullable = false)
  private Boolean multiple = false;

  @Column(name = ExampleConstants.POLL_COLUMN_CHOICES_NAME, nullable = false)
  private List<String> choices;

  @Column(name = ExampleConstants.POLL_COLUMN_VOTES_NAME, nullable = false)
  private List<Integer> votes;

  @Column(name = ExampleConstants.POLL_COLUMN_USERID_NAME, nullable = false, length = 32)
  private String userId;

  @Column(name = ExampleConstants.POLL_COLUMN_USERHOST_NAME, length = 128)
  private String userHost;

  /*
      TODO [JPA Buddy] create field to map the '\"noteVisibility\"' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.POLL_COLUMN_NOTEVISIBILITY_NAME, columnDefinition = "poll_notevisibility_enum(0, 0) not null")private Object noteVisibility;
  */
}
