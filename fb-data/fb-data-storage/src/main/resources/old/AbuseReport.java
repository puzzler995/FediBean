package old;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "abuse_report")
public class AbuseReport extends DBEntity implements Serializable {
  private static final long serialVersionUID = 8388518596095722003L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_user_id", nullable = false)
    private User targetUser;

    @CreatedBy
    @ManyToOne(optional = false)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @Column(name = "is_resolved", nullable = false)
    private Boolean isResolved = false;

    @Lob
    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "is_forwarded", nullable = false)
    private Boolean isForwarded = false;

}
