package example;

import dev.puzzler995.fedibean.data.model.Announcement;
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
@Table(name = ExampleConstants.ANNOUNCEMENTREAD_TABLE_NAME)
public class AnnouncementRead implements Serializable {
  private static final long serialVersionUID = 8460910377865894660L;

  @Id
  @Column(name = ExampleConstants.ANNOUNCEMENTREAD_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"announcementId\"", nullable = false)
  private Announcement announcement;

  @Column(name = ExampleConstants.ANNOUNCEMENTREAD_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
}
