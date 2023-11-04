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
@Table(name = ExampleConstants.CHARTPERUSERDRIVE_TABLE_NAME)
public class ChartPerUserDrive implements Serializable {
  private static final long serialVersionUID = -5203979042576683138L;

  @Id
  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(
      name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_GROUP_NAME,
      nullable = false,
      length = 128)
  private String group;

  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_TOTALCOUNT_NAME, nullable = false)
  private Integer totalcount;

  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_TOTALSIZE_NAME, nullable = false)
  private Integer totalsize;

  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_INCCOUNT_NAME, nullable = false)
  private Short inccount;

  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_INCSIZE_NAME, nullable = false)
  private Integer incsize;

  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_DECCOUNT_NAME, nullable = false)
  private Short deccount;

  @Column(name = ExampleConstants.CHARTPERUSERDRIVE_COLUMN_DECSIZE_NAME, nullable = false)
  private Integer decsize;
}
