package example;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
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
@Table(name = ExampleConstants.ATTESTATIONCHALLENGE_TABLE_NAME)
public class AttestationChallenge implements Serializable {
    private static final long serialVersionUID = 1848791276019254134L;
    @EmbeddedId
    private AttestationChallengeId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"userId\"", nullable = false)
    private User user;

    @Column(name = ExampleConstants.ATTESTATIONCHALLENGE_COLUMN_CHALLENGE_NAME, nullable = false, length = 64)
    private String challenge;

    @Column(name = ExampleConstants.ATTESTATIONCHALLENGE_COLUMN_CREATEDAT_NAME, nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = ExampleConstants.ATTESTATIONCHALLENGE_COLUMN_REGISTRATIONCHALLENGE_NAME, nullable = false)
    private Boolean registrationChallenge = false;

}