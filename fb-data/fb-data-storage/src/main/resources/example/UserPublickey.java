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
@Table(name = ExampleConstants.USERPUBLICKEY_TABLE_NAME)
public class UserPublickey implements Serializable {
  private static final long serialVersionUID = -1154700705043029020L;

  @Id
  @Column(name = ExampleConstants.USERPUBLICKEY_COLUMN_USERID_NAME, nullable = false, length = 32)
  private String userId;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(name = ExampleConstants.USERPUBLICKEY_COLUMN_KEYID_NAME, nullable = false, length = 256)
  private String keyId;

  @Column(name = ExampleConstants.USERPUBLICKEY_COLUMN_KEYPEM_NAME, nullable = false, length = 4096)
  private String keyPem;
}
