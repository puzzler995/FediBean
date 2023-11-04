package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = ExampleConstants.CHARTDAYUSER_TABLE_NAME)
public class ChartDayUser implements Serializable {
  private static final long serialVersionUID = -7093053496699090009L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_LOCALTOTAL_NAME, nullable = false)
  private Integer localTotal;

  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_LOCALINC_NAME, nullable = false)
  private Short localInc;

  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_LOCALDEC_NAME, nullable = false)
  private Short localDec;

  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_REMOTETOTAL_NAME, nullable = false)
  private Integer remoteTotal;

  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_REMOTEINC_NAME, nullable = false)
  private Short remoteInc;

  @Column(name = ExampleConstants.CHARTDAYUSER_COLUMN_REMOTEDEC_NAME, nullable = false)
  private Short remoteDec;
}
