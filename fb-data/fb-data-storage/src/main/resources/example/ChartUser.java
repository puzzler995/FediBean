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
@Table(name = ExampleConstants.CHARTUSER_TABLE_NAME)
public class ChartUser implements Serializable {
  private static final long serialVersionUID = 5321768709871781911L;
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Id
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_ID_NAME, nullable = false)
  private Integer id;
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_LOCALDEC_NAME, nullable = false)
  private Short localDec;
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_LOCALINC_NAME, nullable = false)
  private Short localInc;
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_LOCALTOTAL_NAME, nullable = false)
  private Integer localTotal;
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_REMOTEDEC_NAME, nullable = false)
  private Short remoteDec;
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_REMOTEINC_NAME, nullable = false)
  private Short remoteInc;
  @Column(name = ExampleConstants.CHARTUSER_COLUMN_REMOTETOTAL_NAME, nullable = false)
  private Integer remoteTotal;
}
