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
@Table(name = ExampleConstants.PROMONOTE_TABLE_NAME)
public class PromoNote implements Serializable {
  private static final long serialVersionUID = -2937301961797670659L;

  @Id
  @Column(name = ExampleConstants.PROMONOTE_COLUMN_NOTEID_NAME, nullable = false, length = 32)
  private String noteId;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"noteId\"", nullable = false)
  private Note note;

  @Column(name = ExampleConstants.PROMONOTE_COLUMN_EXPIRESAT_NAME, nullable = false)
  private OffsetDateTime expiresAt;

  @Column(name = ExampleConstants.PROMONOTE_COLUMN_USERID_NAME, nullable = false, length = 32)
  private String userId;
}
