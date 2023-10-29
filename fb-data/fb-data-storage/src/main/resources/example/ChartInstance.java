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
@Table(name = ExampleConstants.CHARTINSTANCE_TABLE_NAME)
public class ChartInstance implements Serializable {
  private static final long serialVersionUID = -5421544237754651794L;

  @Id
  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_DATE_NAME, nullable = false)
  private Integer date;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_GROUP_NAME, nullable = false, length = 128)
  private String group;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_REQUESTSFAILED_NAME, nullable = false)
  private Short requestsFailed;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_REQUESTSSUCCEEDED_NAME, nullable = false)
  private Short requestsSucceeded;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_REQUESTSRECEIVED_NAME, nullable = false)
  private Short requestsReceived;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_NOTESTOTAL_NAME, nullable = false)
  private Integer notesTotal;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_NOTESINC_NAME, nullable = false)
  private Integer notesInc;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_NOTESDEC_NAME, nullable = false)
  private Integer notesDec;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_NOTESDIFFSNORMAL_NAME, nullable = false)
  private Integer notesDiffsNormal;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_NOTESDIFFSREPLY_NAME, nullable = false)
  private Integer notesDiffsReply;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_NOTESDIFFSRENOTE_NAME, nullable = false)
  private Integer notesDiffsRenote;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_USERSTOTAL_NAME, nullable = false)
  private Integer usersTotal;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_USERSINC_NAME, nullable = false)
  private Short usersInc;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_USERSDEC_NAME, nullable = false)
  private Short usersDec;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_FOLLOWINGTOTAL_NAME, nullable = false)
  private Integer followingTotal;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_FOLLOWINGINC_NAME, nullable = false)
  private Short followingInc;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_FOLLOWINGDEC_NAME, nullable = false)
  private Short followingDec;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_FOLLOWERSTOTAL_NAME, nullable = false)
  private Integer followersTotal;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_FOLLOWERSINC_NAME, nullable = false)
  private Short followersInc;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_FOLLOWERSDEC_NAME, nullable = false)
  private Short followersDec;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_DRIVETOTALFILES_NAME, nullable = false)
  private Integer driveTotalfiles;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_DRIVEINCFILES_NAME, nullable = false)
  private Integer driveIncfiles;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_DRIVEINCUSAGE_NAME, nullable = false)
  private Integer driveIncusage;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_DRIVEDECFILES_NAME, nullable = false)
  private Integer driveDecfiles;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_DRIVEDECUSAGE_NAME, nullable = false)
  private Integer driveDecusage;

  @Column(name = ExampleConstants.CHARTINSTANCE_COLUMN_NOTESDIFFSWITHFILE_NAME, nullable = false)
  private Integer notesDiffsWithfile;
}
