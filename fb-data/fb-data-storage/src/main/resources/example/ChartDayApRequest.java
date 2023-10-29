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
@Table(name = ExampleConstants.CHARTDAYAPREQUEST_TABLE_NAME)
public class ChartDayApRequest implements Serializable {
  private static final long serialVersionUID = 7837930608257823849L;

  @Id
  @Column(name = ExampleConstants.CHARTDAYAPREQUEST_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDAYAPREQUEST_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTDAYAPREQUEST_COLUMN_DELIVERFAILED_NAME, nullable = false)
  private Integer deliverfailed;

  @Column(name = ExampleConstants.CHARTDAYAPREQUEST_COLUMN_DELIVERSUCCEEDED_NAME, nullable = false)
  private Integer deliversucceeded;

  @Column(name = ExampleConstants.CHARTDAYAPREQUEST_COLUMN_INBOXRECEIVED_NAME, nullable = false)
  private Integer inboxreceived;
}
