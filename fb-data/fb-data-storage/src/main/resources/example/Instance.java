package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Table(name = ExampleConstants.INSTANCE_TABLE_NAME)
public class Instance implements Serializable {
    private static final long serialVersionUID = 1778157574239976710L;
    @Id
    @Column(name = ExampleConstants.INSTANCE_COLUMN_ID_NAME, nullable = false, length = 32)
    private String id;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_CAUGHTAT_NAME, nullable = false)
    private OffsetDateTime caughtAt;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_HOST_NAME, nullable = false, length = 128)
    private String host;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_USERSCOUNT_NAME, nullable = false)
    private Integer usersCount;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_NOTESCOUNT_NAME, nullable = false)
    private Integer notesCount;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_FOLLOWINGCOUNT_NAME, nullable = false)
    private Integer followingCount;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_FOLLOWERSCOUNT_NAME, nullable = false)
    private Integer followersCount;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_LATESTREQUESTSENTAT_NAME)
    private OffsetDateTime latestRequestSentAt;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_LATESTSTATUS_NAME)
    private Integer latestStatus;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_LATESTREQUESTRECEIVEDAT_NAME)
    private OffsetDateTime latestRequestReceivedAt;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_LASTCOMMUNICATEDAT_NAME, nullable = false)
    private OffsetDateTime lastCommunicatedAt;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_ISNOTRESPONDING_NAME, nullable = false)
    private Boolean isNotResponding = false;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_SOFTWARENAME_NAME, length = 64)
    private String softwareName;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_SOFTWAREVERSION_NAME, length = 64)
    private String softwareVersion;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_OPENREGISTRATIONS_NAME)
    private Boolean openRegistrations;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_NAME_NAME, length = 256)
    private String name;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_DESCRIPTION_NAME, length = 4096)
    private String description;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_MAINTAINERNAME_NAME, length = 128)
    private String maintainerName;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_MAINTAINEREMAIL_NAME, length = 256)
    private String maintainerEmail;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_INFOUPDATEDAT_NAME)
    private OffsetDateTime infoUpdatedAt;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_ISSUSPENDED_NAME, nullable = false)
    private Boolean isSuspended = false;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_ICONURL_NAME, length = 256)
    private String iconUrl;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_THEMECOLOR_NAME, length = 64)
    private String themeColor;

    @Column(name = ExampleConstants.INSTANCE_COLUMN_FAVICONURL_NAME, length = 256)
    private String faviconUrl;

}