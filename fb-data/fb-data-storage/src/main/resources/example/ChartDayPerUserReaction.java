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
@Table(name = ExampleConstants.CHARTDAYPERUSERREACTION_TABLE_NAME)
public class ChartDayPerUserReaction implements Serializable {
  private static final long serialVersionUID = -7283877378734992046L;

  @Id
  @Column(name = ExampleConstants.CHARTDAYPERUSERREACTION_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDAYPERUSERREACTION_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERREACTION_COLUMN_GROUP_NAME,
      nullable = false,
      length = 128)
  private String group;

  @Column(name = ExampleConstants.CHARTDAYPERUSERREACTION_COLUMN_LOCALCOUNT_NAME, nullable = false)
  private Short localCount;

  @Column(name = ExampleConstants.CHARTDAYPERUSERREACTION_COLUMN_REMOTECOUNT_NAME, nullable = false)
  private Short remoteCount;
}
