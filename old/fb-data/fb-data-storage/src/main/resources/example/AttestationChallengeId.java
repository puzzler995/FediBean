package example;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

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
@Embeddable
public class AttestationChallengeId implements Serializable {
  private static final long serialVersionUID = 7605954352912905110L;

  @Column(
      name = ExampleConstants.ATTESTATIONCHALLENGEID_COLUMN_ID_NAME,
      nullable = false,
      length = 32)
  private String id;

  @Column(
      name = ExampleConstants.ATTESTATIONCHALLENGEID_COLUMN_USERID_NAME,
      nullable = false,
      length = 32)
  private String userId;

  @Override
  public int hashCode() {
    return Objects.hash(id, userId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    AttestationChallengeId entity = (AttestationChallengeId) o;
    return Objects.equals(this.id, entity.id) && Objects.equals(this.userId, entity.userId);
  }
}
