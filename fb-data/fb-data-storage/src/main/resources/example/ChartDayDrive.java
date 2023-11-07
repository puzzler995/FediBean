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
@Table(name = ExampleConstants.CHARTDAYDRIVE_TABLE_NAME)
public class ChartDayDrive implements Serializable {
  private static final long serialVersionUID = 7730216295892435417L;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Id
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_ID_NAME, nullable = false)
  private Integer id;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_LOCALDECCOUNT_NAME, nullable = false)
  private Integer localDeccount;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_LOCALDECSIZE_NAME, nullable = false)
  private Integer localDecsize;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_LOCALINCCOUNT_NAME, nullable = false)
  private Integer localInccount;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_LOCALINCSIZE_NAME, nullable = false)
  private Integer localIncsize;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_REMOTEDECCOUNT_NAME, nullable = false)
  private Integer remoteDeccount;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_REMOTEDECSIZE_NAME, nullable = false)
  private Integer remoteDecsize;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_REMOTEINCCOUNT_NAME, nullable = false)
  private Integer remoteInccount;
  @Column(name = ExampleConstants.CHARTDAYDRIVE_COLUMN_REMOTEINCSIZE_NAME, nullable = false)
  private Integer remoteIncsize;
}
