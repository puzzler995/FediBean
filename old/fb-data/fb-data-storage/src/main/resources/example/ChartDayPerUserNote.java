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
@Table(name = ExampleConstants.CHARTDAYPERUSERNOTE_TABLE_NAME)
public class ChartDayPerUserNote implements Serializable {
  private static final long serialVersionUID = -766063958398503176L;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_DEC_NAME, nullable = false)
  private Short dec;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_DIFFSNORMAL_NAME, nullable = false)
  private Short diffsNormal;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_DIFFSRENOTE_NAME, nullable = false)
  private Short diffsRenote;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_DIFFSREPLY_NAME, nullable = false)
  private Short diffsReply;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_DIFFSWITHFILE_NAME, nullable = false)
  private Short diffsWithfile;
  @Column(
      name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_GROUP_NAME,
      nullable = false,
      length = 128)
  private String group;
  @Id
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_ID_NAME, nullable = false)
  private Integer id;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_INC_NAME, nullable = false)
  private Short inc;
  @Column(name = ExampleConstants.CHARTDAYPERUSERNOTE_COLUMN_TOTAL_NAME, nullable = false)
  private Integer total;
}
