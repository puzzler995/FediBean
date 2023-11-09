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
@Table(name = ExampleConstants.CHANNEL_TABLE_NAME)
public class Channel implements Serializable {
  private static final long serialVersionUID = -8788114983697838179L;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"bannerId\"")
  private DriveFile banner;
  @Column(name = ExampleConstants.CHANNEL_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @Column(name = ExampleConstants.CHANNEL_COLUMN_DESCRIPTION_NAME, length = 2048)
  private String description;
  @Id
  @Column(name = ExampleConstants.CHANNEL_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;
  @Column(name = ExampleConstants.CHANNEL_COLUMN_LASTNOTEDAT_NAME)
  private OffsetDateTime lastNotedAt;
  @Column(name = ExampleConstants.CHANNEL_COLUMN_NAME_NAME, nullable = false, length = 128)
  private String name;
  @Column(name = ExampleConstants.CHANNEL_COLUMN_NOTESCOUNT_NAME, nullable = false)
  private Integer notesCount;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"userId\"")
  private User user;
  @Column(name = ExampleConstants.CHANNEL_COLUMN_USERSCOUNT_NAME, nullable = false)
  private Integer usersCount;
}
