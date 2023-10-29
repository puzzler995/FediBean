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
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Table(name = ExampleConstants.RENOTEMUTING_TABLE_NAME)
public class RenoteMuting implements Serializable {
  private static final long serialVersionUID = -8378695648450891768L;

  @Id
  @Column(name = ExampleConstants.RENOTEMUTING_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.RENOTEMUTING_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = ExampleConstants.RENOTEMUTING_COLUMN_MUTEEID_NAME, nullable = false, length = 32)
  private String muteeId;

  @Column(name = ExampleConstants.RENOTEMUTING_COLUMN_MUTERID_NAME, nullable = false, length = 32)
  private String muterId;
}
