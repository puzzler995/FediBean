package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Table(name = ExampleConstants.SEAQLMIGRATION_TABLE_NAME)
public class SeaqlMigration implements Serializable {
  private static final long serialVersionUID = 411406222779434893L;
  @Column(name = ExampleConstants.SEAQLMIGRATION_COLUMN_APPLIEDAT_NAME, nullable = false)
  private Long appliedAt;
  @Id
  @Column(
      name = ExampleConstants.SEAQLMIGRATION_COLUMN_VERSION_NAME,
      nullable = false,
      length = Integer.MAX_VALUE)
  private String version;
}
