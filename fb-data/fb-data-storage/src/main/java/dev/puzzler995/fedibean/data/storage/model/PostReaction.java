package dev.puzzler995.fedibean.data.storage.model;

import dev.puzzler995.fedibean.data.storage.model.constant.DatabaseConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
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
@Table(name = DatabaseConstant.POSTREACTION_TABLE_NAME)
public class PostReaction implements Serializable {
  private static final long serialVersionUID = 3932918677448768515L;
  @Column(name = DatabaseConstant.POSTREACTION_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = DatabaseConstant.POSTREACTION_COLUMN_ID_NAME, nullable = false)
  private UUID id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Column(name = DatabaseConstant.POSTREACTION_COLUMN_REACTION_NAME, nullable = false)
  private String reaction;

  @ManyToOne(optional = false)
  @JoinColumn(
      name = "user_id",
      referencedColumnName = DatabaseConstant.USER_COLUMN_ID_NAME,
      nullable = false)
  private User user;
}
