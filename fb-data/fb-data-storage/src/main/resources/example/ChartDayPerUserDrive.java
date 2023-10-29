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
@Table(name = ExampleConstants.CHARTDAYPERUSERDRIVE_TABLE_NAME)
public class ChartDayPerUserDrive implements Serializable {
  private static final long serialVersionUID = 8497459589882043921L;

  @Id
  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_GROUP_NAME,
      nullable = false,
      length = 128)
  private String group;

  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_TOTALCOUNT_NAME, nullable = false)
  private Integer totalcount;

  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_TOTALSIZE_NAME, nullable = false)
  private Integer totalsize;

  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_INCCOUNT_NAME, nullable = false)
  private Short inccount;

  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_INCSIZE_NAME, nullable = false)
  private Integer incsize;

  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_DECCOUNT_NAME, nullable = false)
  private Short deccount;

  @Column(name = ExampleConstants.CHARTDAYPERUSERDRIVE_COLUMN_DECSIZE_NAME, nullable = false)
  private Integer decsize;
}
