package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import dev.puzzler995.fedibean.data.model.embeds.Attachment;
import dev.puzzler995.fedibean.data.model.embeds.NoteReactionType;
import dev.puzzler995.fedibean.data.model.embeds.Poll;
import dev.puzzler995.fedibean.data.model.embeds.RemoteUserReference;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.net.URI;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.NOTE_TABLE_NAME)
public class Note extends DBItem {
  private static final long serialVersionUID = 7345899325743322756L;
  @ElementCollection
  @CollectionTable(name = "note_attachments", joinColumns = @JoinColumn(name = "owner_id"))
  private List<Attachment> attachments = new ArrayList<>();
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "boost_id")
  private Note boost;
  @Column(name = DBConstant.NOTE_COLUMN_BOOSTCOUNT_NAME, nullable = false)
  private Short boostCount;
  @ManyToMany
  @JoinTable(
      name = DBConstant.NOTE_JOINTABLE_EMOJIS_NAME,
      joinColumns = @JoinColumn(name = DBConstant.NOTE_JOINCOLUMNS_JOINCOLUMN_EMOJIS_NAME),
      inverseJoinColumns =
          @JoinColumn(name = DBConstant.NOTE_INVERSEJOINCOLUMNS_JOINCOLUMN_EMOJIS_NAME))
  private Set<Emoji> emojis = new LinkedHashSet<>();
  @Column(name = DBConstant.NOTE_COLUMN_LOCALONLY_NAME, nullable = false)
  private Boolean localOnly = false;
  @ElementCollection
  @CollectionTable(name = "note_mentionedRemoteUsers", joinColumns = @JoinColumn(name = "owner_id"))
  private List<RemoteUserReference> mentionedRemoteUsers = new ArrayList<>();
  @ManyToMany
  @JoinTable(
      name = DBConstant.NOTE_JOINTABLE_MENTIONS_NAME,
      joinColumns = @JoinColumn(name = DBConstant.NOTE_JOINCOLUMNS_JOINCOLUMN_MENTIONS_NAME),
      inverseJoinColumns =
          @JoinColumn(name = DBConstant.NOTE_INVERSEJOINCOLUMNS_JOINCOLUMN_MENTIONS_NAME))
  private Set<User> mentions = new LinkedHashSet<>();
  @Column(name = DBConstant.NOTE_COLUMN_NAME_NAME)
  private String name;
  @Embedded private Poll poll;
  @ElementCollection
  @CollectionTable(name = "note_reactions", joinColumns = @JoinColumn(name = "owner_id"))
  private List<NoteReactionType> reactions = new ArrayList<>();
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "reply_id")
  private Note reply;
  @Column(name = DBConstant.NOTE_COLUMN_REPLYCOUNT_NAME, nullable = false)
  private Short replyCount;
  @Column(name = DBConstant.NOTE_COLUMN_SCORE_NAME, nullable = false)
  private Integer score;
  @Lob
  @Column(name = DBConstant.NOTE_COLUMN_SUMMARY_NAME)
  private String summary;
  @ElementCollection
  @Column(name = DBConstant.NOTE_COLUMN_TAGS_NAME)
  @CollectionTable(name = "note_tags", joinColumns = @JoinColumn(name = "owner_id"))
  private Set<String> tags = new LinkedHashSet<>();
  @Lob
  @Column(name = DBConstant.NOTE_COLUMN_TEXT_NAME)
  private String text;
  @Column(name = DBConstant.NOTE_COLUMN_THREADID_NAME)
  private UUID threadId;
  @LastModifiedDate
  @Column(name = DBConstant.NOTE_COLUMN_UPDATED_NAME)
  private OffsetDateTime updated;
  @Column(name = DBConstant.NOTE_COLUMN_URI_NAME, length = 512)
  private URI uri;
  @Column(name = DBConstant.NOTE_COLUMN_URL_NAME, length = 512)
  private URL url;
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @Enumerated
  @Column(name = DBConstant.NOTE_COLUMN_VISIBILITY_NAME, nullable = false)
  private VISIBILITY_TYPE visibility;
  @ManyToMany
  @JoinTable(
      name = DBConstant.NOTE_JOINTABLE_VISIBLETO_NAME,
      joinColumns = @JoinColumn(name = DBConstant.NOTE_JOINCOLUMNS_JOINCOLUMN_VISIBLETO_NAME),
      inverseJoinColumns =
          @JoinColumn(name = DBConstant.NOTE_INVERSEJOINCOLUMNS_JOINCOLUMN_VISIBLETO_NAME))
  private Set<User> visibleTo = new LinkedHashSet<>();

  private enum VISIBILITY_TYPE {
    PUBLIC,
    HOME,
    FOLLOWERS,
    SPECIFIED,
    PRIVATE
  }
}
