package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Table(name = ExampleConstants.NOTEUNREAD_TABLE_NAME)
public class NoteUnread implements Serializable {
    private static final long serialVersionUID = 7021376656690843835L;
    @Id
    @Column(name = ExampleConstants.NOTEUNREAD_COLUMN_ID_NAME, nullable = false, length = 32)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"userId\"", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"noteId\"", nullable = false)
    private Note note;

    @Column(name = ExampleConstants.NOTEUNREAD_COLUMN_NOTEUSERID_NAME, nullable = false, length = 32)
    private String noteUserId;

    @Column(name = ExampleConstants.NOTEUNREAD_COLUMN_ISSPECIFIED_NAME, nullable = false)
    private Boolean isSpecified = false;

    @Column(name = ExampleConstants.NOTEUNREAD_COLUMN_ISMENTIONED_NAME, nullable = false)
    private Boolean isMentioned = false;

    @Column(name = ExampleConstants.NOTEUNREAD_COLUMN_NOTECHANNELID_NAME, length = 32)
    private String noteChannelId;

}