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
@Table(name = ExampleConstants.RELAY_TABLE_NAME)
public class Relay implements Serializable {
  private static final long serialVersionUID = -3237617495362873244L;

  @Id
  @Column(name = ExampleConstants.RELAY_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.RELAY_COLUMN_INBOX_NAME, nullable = false, length = 512)
  private String inbox;

  /*
      TODO [JPA Buddy] create field to map the 'status' column
       Available actions: Define target Java type | Uncomment as is | Remove column mapping
      @Column(name = ExampleConstants.RELAY_COLUMN_STATUS_NAME, columnDefinition = "relay_status_enum(0, 0) not null")private Object status;
  */
}
