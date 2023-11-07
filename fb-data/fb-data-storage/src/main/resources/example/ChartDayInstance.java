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
@Table(name = ExampleConstants.CHARTDAYINSTANCE_TABLE_NAME)
public class ChartDayInstance implements Serializable {
  private static final long serialVersionUID = 4887623139909076692L;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_DRIVEDECFILES_NAME, nullable = false)
  private Integer driveDecfiles;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_DRIVEDECUSAGE_NAME, nullable = false)
  private Integer driveDecusage;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_DRIVEINCFILES_NAME, nullable = false)
  private Integer driveIncfiles;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_DRIVEINCUSAGE_NAME, nullable = false)
  private Integer driveIncusage;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_DRIVETOTALFILES_NAME, nullable = false)
  private Integer driveTotalfiles;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_FOLLOWERSDEC_NAME, nullable = false)
  private Short followersDec;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_FOLLOWERSINC_NAME, nullable = false)
  private Short followersInc;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_FOLLOWERSTOTAL_NAME, nullable = false)
  private Integer followersTotal;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_FOLLOWINGDEC_NAME, nullable = false)
  private Short followingDec;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_FOLLOWINGINC_NAME, nullable = false)
  private Short followingInc;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_FOLLOWINGTOTAL_NAME, nullable = false)
  private Integer followingTotal;
  @Column(
      name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_GROUP_NAME,
      nullable = false,
      length = 128)
  private String group;
  @Id
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_ID_NAME, nullable = false)
  private Integer id;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_NOTESDEC_NAME, nullable = false)
  private Integer notesDec;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_NOTESDIFFSNORMAL_NAME, nullable = false)
  private Integer notesDiffsNormal;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_NOTESDIFFSRENOTE_NAME, nullable = false)
  private Integer notesDiffsRenote;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_NOTESDIFFSREPLY_NAME, nullable = false)
  private Integer notesDiffsReply;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_NOTESDIFFSWITHFILE_NAME, nullable = false)
  private Integer notesDiffsWithfile;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_NOTESINC_NAME, nullable = false)
  private Integer notesInc;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_NOTESTOTAL_NAME, nullable = false)
  private Integer notesTotal;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_REQUESTSFAILED_NAME, nullable = false)
  private Short requestsFailed;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_REQUESTSRECEIVED_NAME, nullable = false)
  private Short requestsReceived;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_REQUESTSSUCCEEDED_NAME, nullable = false)
  private Short requestsSucceeded;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_USERSDEC_NAME, nullable = false)
  private Short usersDec;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_USERSINC_NAME, nullable = false)
  private Short usersInc;
  @Column(name = ExampleConstants.CHARTDAYINSTANCE_COLUMN_USERSTOTAL_NAME, nullable = false)
  private Integer usersTotal;
}
