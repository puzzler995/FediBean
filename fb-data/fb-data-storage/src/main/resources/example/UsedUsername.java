package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
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
@Table(name = ExampleConstants.USEDUSERNAME_TABLE_NAME)
public class UsedUsername implements Serializable {
  private static final long serialVersionUID = -8519558392997914976L;

  @Id
  @Column(name = ExampleConstants.USEDUSERNAME_COLUMN_USERNAME_NAME, nullable = false, length = 128)
  private String username;

  @Column(name = ExampleConstants.USEDUSERNAME_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
}
