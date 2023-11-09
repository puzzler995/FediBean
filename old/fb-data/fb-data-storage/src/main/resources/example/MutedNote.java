package example;

import dev.puzzler995.fedibean.data.model.Note;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Table(name = ExampleConstants.MUTEDNOTE_TABLE_NAME)
public class MutedNote implements Serializable {
  private static final long serialVersionUID = -1612806268371149202L;

  @Id
  @Column(name = ExampleConstants.MUTEDNOTE_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"noteId\"", nullable = false)
  private Note note;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  /*
      TODO [JPA Buddy] create field to map the 'reason' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.MUTEDNOTE_COLUMN_REASON_NAME, columnDefinition = "muted_note_reason_enum(0, 0) not null")private Object reason;
  */
}
