package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
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
@Table(name = ExampleConstants.CHARTTESTUNIQUE_TABLE_NAME)
public class ChartTestUnique implements Serializable {
  private static final long serialVersionUID = 6164386692668302652L;

  @Id
  @Column(name = ExampleConstants.CHARTTESTUNIQUE_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTTESTUNIQUE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTTESTUNIQUE_COLUMN_GROUP_NAME, length = 128)
  private String group;

  @Column(name = ExampleConstants.CHARTTESTUNIQUE_COLUMN_FOO_NAME, nullable = false)
  private List<String> foo;
}
