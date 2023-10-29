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
@Table(name = ExampleConstants.CHARTDRIVE_TABLE_NAME)
public class ChartDrive implements Serializable {
  private static final long serialVersionUID = 494219225431952425L;

  @Id
  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_LOCALINCCOUNT_NAME, nullable = false)
  private Integer localInccount;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_LOCALINCSIZE_NAME, nullable = false)
  private Integer localIncsize;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_LOCALDECCOUNT_NAME, nullable = false)
  private Integer localDeccount;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_LOCALDECSIZE_NAME, nullable = false)
  private Integer localDecsize;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_REMOTEINCCOUNT_NAME, nullable = false)
  private Integer remoteInccount;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_REMOTEINCSIZE_NAME, nullable = false)
  private Integer remoteIncsize;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_REMOTEDECCOUNT_NAME, nullable = false)
  private Integer remoteDeccount;

  @Column(name = ExampleConstants.CHARTDRIVE_COLUMN_REMOTEDECSIZE_NAME, nullable = false)
  private Integer remoteDecsize;
}
