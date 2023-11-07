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
@Table(name = ExampleConstants.CHARTPERUSERNOTE_TABLE_NAME)
public class ChartPerUserNote implements Serializable {
  private static final long serialVersionUID = -1641586819954536237L;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_DEC_NAME, nullable = false)
  private Short dec;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_DIFFSNORMAL_NAME, nullable = false)
  private Short diffsNormal;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_DIFFSRENOTE_NAME, nullable = false)
  private Short diffsRenote;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_DIFFSREPLY_NAME, nullable = false)
  private Short diffsReply;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_DIFFSWITHFILE_NAME, nullable = false)
  private Short diffsWithfile;
  @Column(
      name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_GROUP_NAME,
      nullable = false,
      length = 128)
  private String group;
  @Id
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_ID_NAME, nullable = false)
  private Integer id;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_INC_NAME, nullable = false)
  private Short inc;
  @Column(name = ExampleConstants.CHARTPERUSERNOTE_COLUMN_TOTAL_NAME, nullable = false)
  private Integer total;
}
