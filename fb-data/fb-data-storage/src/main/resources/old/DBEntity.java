package old;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
public class DBEntity implements Serializable {
  private static final long serialVersionUID = 1500064012215826736L;
  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private ZonedDateTime createdAt;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;
}
