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
@Table(name = ExampleConstants.CHARTAPREQUEST_TABLE_NAME)
public class ChartApRequest implements Serializable {
  private static final long serialVersionUID = 3378384637252222220L;

  @Id
  @Column(name = ExampleConstants.CHARTAPREQUEST_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTAPREQUEST_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTAPREQUEST_COLUMN_DELIVERFAILED_NAME, nullable = false)
  private Integer deliverfailed;

  @Column(name = ExampleConstants.CHARTAPREQUEST_COLUMN_DELIVERSUCCEEDED_NAME, nullable = false)
  private Integer deliversucceeded;

  @Column(name = ExampleConstants.CHARTAPREQUEST_COLUMN_INBOXRECEIVED_NAME, nullable = false)
  private Integer inboxreceived;
}
