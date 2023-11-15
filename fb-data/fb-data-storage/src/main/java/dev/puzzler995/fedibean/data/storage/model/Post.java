package dev.puzzler995.fedibean.data.storage.model;

import dev.puzzler995.fedibean.data.storage.model.constant.DatabaseConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = DatabaseConstant.POST_TABLE_NAME)
public class Post implements Serializable {
  private static final long serialVersionUID = -8903887254353202742L;

  @ManyToMany
  @JoinTable(
      name = DatabaseConstant.POST_JOINTABLE_ATTACHMENTS_NAME,
      joinColumns =
          @JoinColumn(
              name = DatabaseConstant.POST_JOINCOLUMNS_JOINCOLUMN_ATTACHMENTS_NAME,
              referencedColumnName = DatabaseConstant.POST_COLUMN_ID_NAME),
      inverseJoinColumns =
          @JoinColumn(
              name = DatabaseConstant.POST_INVERSEJOINCOLUMNS_JOINCOLUMN_ATTACHMENTS_NAME,
              referencedColumnName = DatabaseConstant.ARTIFACT_COLUMN_ID_NAME))
  private Set<Artifact> attachments = new LinkedHashSet<>();

  @ManyToOne
  @JoinColumn(name = "boosted_post_id", referencedColumnName = DatabaseConstant.POST_COLUMN_ID_NAME)
  private Post boostedPost;

  @Lob
  @Column(name = DatabaseConstant.POST_COLUMN_CONTENT_NAME)
  private String content;

  @Column(name = DatabaseConstant.POST_COLUMN_CREATED_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = DatabaseConstant.POST_COLUMN_ID_NAME, nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = DatabaseConstant.POST_COLUMN_POSTED_BY_NAME,
      referencedColumnName = DatabaseConstant.USER_COLUMN_ID_NAME,
      nullable = false)
  private User postedBy;

  @OneToMany(mappedBy = "post", orphanRemoval = true)
  private List<PostReaction> reactions = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "reply_to_id", referencedColumnName = DatabaseConstant.POST_COLUMN_ID_NAME)
  private Post replyTo;

  @Lob
  @Column(name = DatabaseConstant.POST_COLUMN_SUMMARY_NAME)
  private String summary;
}
