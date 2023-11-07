package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
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
@Table(name = DBConstant.MUTE_TABLE_NAME)
public class Mute extends DBItem {
  private static final long serialVersionUID = -7220776285849647754L;
  @Column(name = DBConstant.MUTE_COLUMN_EXPIRES_NAME)
  private OffsetDateTime expires;
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "muter_id", nullable = false)
  private User muter;
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "target_id", nullable = false)
  private User target;
}
