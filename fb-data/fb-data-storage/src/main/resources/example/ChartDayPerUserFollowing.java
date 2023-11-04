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
@Table(name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_TABLE_NAME)
public class ChartDayPerUserFollowing implements Serializable {
  private static final long serialVersionUID = 4598241711121109307L;

  @Id
  @Column(name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_GROUP_NAME,
      nullable = false,
      length = 128)
  private String group;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_LOCALFOLLOWINGSTOTAL_NAME,
      nullable = false)
  private Integer localFollowingsTotal;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_LOCALFOLLOWINGSINC_NAME,
      nullable = false)
  private Short localFollowingsInc;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_LOCALFOLLOWINGSDEC_NAME,
      nullable = false)
  private Short localFollowingsDec;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_LOCALFOLLOWERSTOTAL_NAME,
      nullable = false)
  private Integer localFollowersTotal;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_LOCALFOLLOWERSINC_NAME,
      nullable = false)
  private Short localFollowersInc;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_LOCALFOLLOWERSDEC_NAME,
      nullable = false)
  private Short localFollowersDec;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_REMOTEFOLLOWINGSTOTAL_NAME,
      nullable = false)
  private Integer remoteFollowingsTotal;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_REMOTEFOLLOWINGSINC_NAME,
      nullable = false)
  private Short remoteFollowingsInc;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_REMOTEFOLLOWINGSDEC_NAME,
      nullable = false)
  private Short remoteFollowingsDec;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_REMOTEFOLLOWERSTOTAL_NAME,
      nullable = false)
  private Integer remoteFollowersTotal;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_REMOTEFOLLOWERSINC_NAME,
      nullable = false)
  private Short remoteFollowersInc;

  @Column(
      name = ExampleConstants.CHARTDAYPERUSERFOLLOWING_COLUMN_REMOTEFOLLOWERSDEC_NAME,
      nullable = false)
  private Short remoteFollowersDec;
}
