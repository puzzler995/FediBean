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
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

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
@Table(name = ExampleConstants.PAGE_TABLE_NAME)
public class Page implements Serializable {
    private static final long serialVersionUID = 8565460929253142210L;
    @Id
    @Column(name = ExampleConstants.PAGE_COLUMN_ID_NAME, nullable = false, length = 32)
    private String id;

    @Column(name = ExampleConstants.PAGE_COLUMN_CREATEDAT_NAME, nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = ExampleConstants.PAGE_COLUMN_UPDATEDAT_NAME, nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = ExampleConstants.PAGE_COLUMN_TITLE_NAME, nullable = false, length = 256)
    private String title;

    @Column(name = ExampleConstants.PAGE_COLUMN_NAME_NAME, nullable = false, length = 256)
    private String name;

    @Column(name = ExampleConstants.PAGE_COLUMN_SUMMARY_NAME, length = 256)
    private String summary;

    @Column(name = ExampleConstants.PAGE_COLUMN_ALIGNCENTER_NAME, nullable = false)
    private Boolean alignCenter = false;

    @Column(name = ExampleConstants.PAGE_COLUMN_FONT_NAME, nullable = false, length = 32)
    private String font;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"userId\"", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"eyeCatchingImageId\"")
    private DriveFile eyeCatchingImage;

    @Column(name = ExampleConstants.PAGE_COLUMN_CONTENT_NAME, nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> content;

    @Column(name = ExampleConstants.PAGE_COLUMN_VARIABLES_NAME, nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> variables;

    @Column(name = ExampleConstants.PAGE_COLUMN_VISIBLEUSERIDS_NAME, nullable = false)
    private List<String> visibleUserIds;

    @Column(name = ExampleConstants.PAGE_COLUMN_LIKEDCOUNT_NAME, nullable = false)
    private Integer likedCount;

    @Column(name = ExampleConstants.PAGE_COLUMN_HIDETITLEWHENPINNED_NAME, nullable = false)
    private Boolean hideTitleWhenPinned = false;

    @Column(name = ExampleConstants.PAGE_COLUMN_SCRIPT_NAME, nullable = false, length = 16384)
    private String script;
    @Column(name = ExampleConstants.PAGE_COLUMN_ISPUBLIC_NAME, nullable = false)
    private Boolean isPublic = false;

/*
    TODO [JPA Buddy] create field to map the 'visibility' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = ExampleConstants.PAGE_COLUMN_VISIBILITY_NAME, columnDefinition = "page_visibility_enum(0, 0) not null")private Object visibility;
*/
}