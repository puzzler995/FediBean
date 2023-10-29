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
import java.util.List;
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
@Table(name = ExampleConstants.MESSAGINGMESSAGE_TABLE_NAME)
public class MessagingMessage implements Serializable {
    private static final long serialVersionUID = 2699086535762401874L;
    @Id
    @Column(name = ExampleConstants.MESSAGINGMESSAGE_COLUMN_ID_NAME, nullable = false, length = 32)
    private String id;

    @Column(name = ExampleConstants.MESSAGINGMESSAGE_COLUMN_CREATEDAT_NAME, nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"userId\"", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"recipientId\"")
    private User recipient;

    @Column(name = ExampleConstants.MESSAGINGMESSAGE_COLUMN_TEXT_NAME, length = 4096)
    private String text;

    @Column(name = ExampleConstants.MESSAGINGMESSAGE_COLUMN_ISREAD_NAME, nullable = false)
    private Boolean isRead = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"fileId\"")
    private DriveFile file;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"groupId\"")
    private UserGroup group;

    @Column(name = ExampleConstants.MESSAGINGMESSAGE_COLUMN_READS_NAME, nullable = false)
    private List<String> reads;

    @Column(name = ExampleConstants.MESSAGINGMESSAGE_COLUMN_URI_NAME, length = 512)
    private String uri;

}