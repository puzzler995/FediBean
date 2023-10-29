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
@Table(name = ExampleConstants.CHARTDAYNOTE_TABLE_NAME)
public class ChartDayNote implements Serializable {
  private static final long serialVersionUID = -4981494122507080254L;

  @Id
  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_LOCALTOTAL_NAME, nullable = false)
  private Integer localTotal;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_LOCALINC_NAME, nullable = false)
  private Integer localInc;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_LOCALDEC_NAME, nullable = false)
  private Integer localDec;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_LOCALDIFFSNORMAL_NAME, nullable = false)
  private Integer localDiffsNormal;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_LOCALDIFFSREPLY_NAME, nullable = false)
  private Integer localDiffsReply;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_LOCALDIFFSRENOTE_NAME, nullable = false)
  private Integer localDiffsRenote;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_REMOTETOTAL_NAME, nullable = false)
  private Integer remoteTotal;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_REMOTEINC_NAME, nullable = false)
  private Integer remoteInc;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_REMOTEDEC_NAME, nullable = false)
  private Integer remoteDec;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_REMOTEDIFFSNORMAL_NAME, nullable = false)
  private Integer remoteDiffsNormal;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_REMOTEDIFFSREPLY_NAME, nullable = false)
  private Integer remoteDiffsReply;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_REMOTEDIFFSRENOTE_NAME, nullable = false)
  private Integer remoteDiffsRenote;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_LOCALDIFFSWITHFILE_NAME, nullable = false)
  private Integer localDiffsWithfile;

  @Column(name = ExampleConstants.CHARTDAYNOTE_COLUMN_REMOTEDIFFSWITHFILE_NAME, nullable = false)
  private Integer remoteDiffsWithfile;
}
