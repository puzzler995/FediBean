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
@Table(name = ExampleConstants.USERNOTEPINING_TABLE_NAME)
public class UserNotePining implements Serializable {
  private static final long serialVersionUID = -2848321588664024967L;

  @Id
  @Column(name = ExampleConstants.USERNOTEPINING_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.USERNOTEPINING_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"noteId\"", nullable = false)
  private Note note;
}
