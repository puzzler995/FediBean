package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.ABUSEREPORT_TABLE_NAME)
public class AbuseReport extends DBItem implements Serializable {
  private static final long serialVersionUID = 1343207461049857226L;
  @ManyToOne
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "assigned_to_id")
  private User assignedTo;
  @Lob
  @Column(name = DBConstant.ABUSEREPORT_COLUMN_COMMENT_NAME, nullable = false)
  private String comment;
  @Column(name = DBConstant.ABUSEREPORT_COLUMN_ISFORWARDED_NAME, nullable = false)
  private Boolean isForwarded = false;
  @Column(name = DBConstant.ABUSEREPORT_COLUMN_ISRESOLVED_NAME, nullable = false)
  private Boolean isResolved = false;
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "reporter_id", nullable = false)
  private User reporter;
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "target_id", nullable = false)
  private User target;
}
