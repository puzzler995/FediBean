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
@Table(name = ExampleConstants.NOTEEDIT_TABLE_NAME)
public class NoteEdit implements Serializable {
  private static final long serialVersionUID = -809105644444501507L;

  @Id
  @Column(name = ExampleConstants.NOTEEDIT_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"noteId\"", nullable = false)
  private Note note;

  @Column(name = ExampleConstants.NOTEEDIT_COLUMN_TEXT_NAME, length = Integer.MAX_VALUE)
  private String text;

  @Column(name = ExampleConstants.NOTEEDIT_COLUMN_CW_NAME, length = 512)
  private String cw;

  @Column(name = ExampleConstants.NOTEEDIT_COLUMN_FILEIDS_NAME, nullable = false)
  private List<String> fileIds;

  @Column(name = ExampleConstants.NOTEEDIT_COLUMN_UPDATEDAT_NAME, nullable = false)
  private OffsetDateTime updatedAt;
}
