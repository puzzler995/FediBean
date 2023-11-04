package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = ExampleConstants.USERSECURITYKEY_TABLE_NAME)
public class UserSecurityKey implements Serializable {
  private static final long serialVersionUID = -3720000602228592243L;

  @Id
  @Column(
      name = ExampleConstants.USERSECURITYKEY_COLUMN_ID_NAME,
      nullable = false,
      length = Integer.MAX_VALUE)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(
      name = ExampleConstants.USERSECURITYKEY_COLUMN_PUBLICKEY_NAME,
      nullable = false,
      length = Integer.MAX_VALUE)
  private String publicKey;

  @Column(name = ExampleConstants.USERSECURITYKEY_COLUMN_LASTUSED_NAME, nullable = false)
  private OffsetDateTime lastUsed;

  @Column(name = ExampleConstants.USERSECURITYKEY_COLUMN_NAME_NAME, nullable = false, length = 30)
  private String name;
}
