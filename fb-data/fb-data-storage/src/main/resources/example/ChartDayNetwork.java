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
@Table(name = ExampleConstants.CHARTDAYNETWORK_TABLE_NAME)
public class ChartDayNetwork implements Serializable {
  private static final long serialVersionUID = -1967410515352235004L;

  @Id
  @Column(name = ExampleConstants.CHARTDAYNETWORK_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDAYNETWORK_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTDAYNETWORK_COLUMN_INCOMINGREQUESTS_NAME, nullable = false)
  private Integer incomingrequests;

  @Column(name = ExampleConstants.CHARTDAYNETWORK_COLUMN_OUTGOINGREQUESTS_NAME, nullable = false)
  private Integer outgoingrequests;

  @Column(name = ExampleConstants.CHARTDAYNETWORK_COLUMN_TOTALTIME_NAME, nullable = false)
  private Integer totaltime;

  @Column(name = ExampleConstants.CHARTDAYNETWORK_COLUMN_INCOMINGBYTES_NAME, nullable = false)
  private Integer incomingbytes;

  @Column(name = ExampleConstants.CHARTDAYNETWORK_COLUMN_OUTGOINGBYTES_NAME, nullable = false)
  private Integer outgoingbytes;
}
