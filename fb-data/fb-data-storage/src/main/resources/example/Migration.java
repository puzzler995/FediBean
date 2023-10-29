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
@Table(name = ExampleConstants.MIGRATION_TABLE_NAME)
public class Migration implements Serializable {
  private static final long serialVersionUID = 5019194812268976608L;

  @Id
  @Column(name = ExampleConstants.MIGRATION_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.MIGRATION_COLUMN_TIMESTAMP_NAME, nullable = false)
  private Long timestamp;

  @Column(
      name = ExampleConstants.MIGRATION_COLUMN_NAME_NAME,
      nullable = false,
      length = Integer.MAX_VALUE)
  private String name;
}
