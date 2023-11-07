package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Table(name = ExampleConstants.USERKEYPAIR_TABLE_NAME)
public class UserKeypair implements Serializable {
  private static final long serialVersionUID = 7800680959670992335L;
  @Column(
      name = ExampleConstants.USERKEYPAIR_COLUMN_PRIVATEKEY_NAME,
      nullable = false,
      length = 4096)
  private String privateKey;
  @Column(
      name = ExampleConstants.USERKEYPAIR_COLUMN_PUBLICKEY_NAME,
      nullable = false,
      length = 4096)
  private String publicKey;
  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;
  @Id
  @Column(name = ExampleConstants.USERKEYPAIR_COLUMN_USERID_NAME, nullable = false, length = 32)
  private String userId;
}
