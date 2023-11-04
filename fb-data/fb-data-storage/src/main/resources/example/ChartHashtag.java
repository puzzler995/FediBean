package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
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
@Table(name = ExampleConstants.CHARTHASHTAG_TABLE_NAME)
public class ChartHashtag implements Serializable {
  private static final long serialVersionUID = 8680992700387991338L;

  @Id
  @Column(name = ExampleConstants.CHARTHASHTAG_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTHASHTAG_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTHASHTAG_COLUMN_GROUP_NAME, nullable = false, length = 128)
  private String group;

  @Column(name = ExampleConstants.CHARTHASHTAG_COLUMN_LOCALUSERS_NAME, nullable = false)
  private Integer localUsers;

  @Column(name = ExampleConstants.CHARTHASHTAG_COLUMN_REMOTEUSERS_NAME, nullable = false)
  private Integer remoteUsers;

  @Column(name = ExampleConstants.CHARTHASHTAG_COLUMN_UNIQUETEMPLOCALUSERS_NAME, nullable = false)
  private List<String> uniqueTempLocalUsers;

  @Column(name = ExampleConstants.CHARTHASHTAG_COLUMN_UNIQUETEMPREMOTEUSERS_NAME, nullable = false)
  private List<String> uniqueTempRemoteUsers;
}
