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
@Table(name = ExampleConstants.CHARTTEST_TABLE_NAME)
public class ChartTest implements Serializable {
  private static final long serialVersionUID = 5168870565764385083L;
  @Column(name = ExampleConstants.CHARTTEST_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Column(name = ExampleConstants.CHARTTEST_COLUMN_FOODEC_NAME, nullable = false)
  private Long fooDec;
  @Column(name = ExampleConstants.CHARTTEST_COLUMN_FOOINC_NAME, nullable = false)
  private Long fooInc;
  @Column(name = ExampleConstants.CHARTTEST_COLUMN_FOOTOTAL_NAME, nullable = false)
  private Long fooTotal;
  @Column(name = ExampleConstants.CHARTTEST_COLUMN_GROUP_NAME, length = 128)
  private String group;
  @Id
  @Column(name = ExampleConstants.CHARTTEST_COLUMN_ID_NAME, nullable = false)
  private Integer id;
}
