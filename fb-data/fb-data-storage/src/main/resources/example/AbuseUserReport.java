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
@Table(name = ExampleConstants.ABUSEUSERREPORT_TABLE_NAME)
public class AbuseUserReport implements Serializable {
    private static final long serialVersionUID = -167431642674549287L;
    @Id
    @Column(name = ExampleConstants.ABUSEUSERREPORT_COLUMN_ID_NAME, nullable = false, length = 32)
    private String id;

    @Column(name = ExampleConstants.ABUSEUSERREPORT_COLUMN_CREATEDAT_NAME, nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"targetUserId\"", nullable = false)
    private User targetUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"reporterId\"", nullable = false)
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "\"assigneeId\"")
    private User assignee;

    @Column(name = ExampleConstants.ABUSEUSERREPORT_COLUMN_RESOLVED_NAME, nullable = false)
    private Boolean resolved = false;

    @Column(name = ExampleConstants.ABUSEUSERREPORT_COLUMN_COMMENT_NAME, nullable = false, length = 2048)
    private String comment;

    @Column(name = ExampleConstants.ABUSEUSERREPORT_COLUMN_TARGETUSERHOST_NAME, length = 128)
    private String targetUserHost;

    @Column(name = ExampleConstants.ABUSEUSERREPORT_COLUMN_REPORTERHOST_NAME, length = 128)
    private String reporterHost;

    @Column(name = ExampleConstants.ABUSEUSERREPORT_COLUMN_FORWARDED_NAME, nullable = false)
    private Boolean forwarded = false;

}