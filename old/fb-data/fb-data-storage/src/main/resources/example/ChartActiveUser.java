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
@Table(name = ExampleConstants.CHARTACTIVEUSER_TABLE_NAME)
public class ChartActiveUser implements Serializable {
  private static final long serialVersionUID = -4316294259977680822L;
  @Column(name = ExampleConstants.CHARTACTIVEUSER_COLUMN_DATE_NAME, nullable = false)
  private Integer date;
  @Id
  @Column(name = ExampleConstants.CHARTACTIVEUSER_COLUMN_ID_NAME, nullable = false)
  private Integer id;
  @Column(name = ExampleConstants.CHARTACTIVEUSER_COLUMN_READ_NAME, nullable = false)
  private Short read;
  @Column(name = ExampleConstants.CHARTACTIVEUSER_COLUMN_READWRITE_NAME, nullable = false)
  private Short readwrite;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_REGISTEREDOUTSIDEMONTH_NAME,
      nullable = false)
  private Short registeredoutsidemonth;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_REGISTEREDOUTSIDEWEEK_NAME,
      nullable = false)
  private Short registeredoutsideweek;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_REGISTEREDOUTSIDEYEAR_NAME,
      nullable = false)
  private Short registeredoutsideyear;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_REGISTEREDWITHINMONTH_NAME,
      nullable = false)
  private Short registeredwithinmonth;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_REGISTEREDWITHINWEEK_NAME,
      nullable = false)
  private Short registeredwithinweek;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_REGISTEREDWITHINYEAR_NAME,
      nullable = false)
  private Short registeredwithinyear;
  @Column(name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPREAD_NAME, nullable = false)
  private List<String> uniqueTempRead;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPREGISTEREDOUTSIDEMONTH_NAME,
      nullable = false)
  private List<String> uniqueTempRegisteredoutsidemonth;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPREGISTEREDOUTSIDEWEEK_NAME,
      nullable = false)
  private List<String> uniqueTempRegisteredoutsideweek;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPREGISTEREDOUTSIDEYEAR_NAME,
      nullable = false)
  private List<String> uniqueTempRegisteredoutsideyear;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPREGISTEREDWITHINMONTH_NAME,
      nullable = false)
  private List<String> uniqueTempRegisteredwithinmonth;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPREGISTEREDWITHINWEEK_NAME,
      nullable = false)
  private List<String> uniqueTempRegisteredwithinweek;
  @Column(
      name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPREGISTEREDWITHINYEAR_NAME,
      nullable = false)
  private List<String> uniqueTempRegisteredwithinyear;
  @Column(name = ExampleConstants.CHARTACTIVEUSER_COLUMN_UNIQUETEMPWRITE_NAME, nullable = false)
  private List<String> uniqueTempWrite;

  @Column(name = ExampleConstants.CHARTACTIVEUSER_COLUMN_WRITE_NAME, nullable = false)
  private Short write;
}
