package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = DBConstant.POLLVOTE_TABLE_NAME)
public class PollVote extends DBItem {
  private static final long serialVersionUID = 6889308556785567755L;
  @Column(name = DBConstant.POLLVOTE_COLUMN_CHOICE_NAME, nullable = false)
  private Integer choice;
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "note_id", nullable = false)
  private Note note;
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
