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
@Table(name = ExampleConstants.CHARTDAYFEDERATION_TABLE_NAME)
public class ChartDayFederation implements Serializable {
  private static final long serialVersionUID = -52823158132926454L;
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Column(
      name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_DELIVEREDINSTANCES_NAME,
      nullable = false)
  private Short deliveredinstances;
  @Id
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_ID_NAME, nullable = false)
  private Integer id;
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_INBOXINSTANCES_NAME, nullable = false)
  private Short inboxinstances;
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_PUB_NAME, nullable = false)
  private Short pub;
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_PUBACTIVE_NAME, nullable = false)
  private Short pubactive;
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_PUBSUB_NAME, nullable = false)
  private Short pubsub;
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_STALLED_NAME, nullable = false)
  private Short stalled;

  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_SUB_NAME, nullable = false)
  private Short sub;
  @Column(name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_SUBACTIVE_NAME, nullable = false)
  private Short subactive;
  @Column(
      name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_UNIQUETEMPDELIVEREDINSTANCES_NAME,
      nullable = false)
  private List<String> uniqueTempDeliveredinstances;
  @Column(
      name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_UNIQUETEMPINBOXINSTANCES_NAME,
      nullable = false)
  private List<String> uniqueTempInboxinstances;
  @Column(
      name = ExampleConstants.CHARTDAYFEDERATION_COLUMN_UNIQUETEMPSTALLED_NAME,
      nullable = false)
  private List<String> uniqueTempStalled;
}
