package dev.puzzler995.fedibean.data.model.embeds;

import dev.puzzler995.fedibean.data.model.Note;
import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Embeddable
public class Poll implements Serializable {
  private static final long serialVersionUID = -422480424560574492L;

  @OneToOne(optional = false, orphanRemoval = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "note_id", nullable = false)
  private Note note;

  @Column(name = DBConstant.POLL_COLUMN_EXPIRES_NAME)
  private OffsetDateTime expires;

  @Column(name = DBConstant.POLL_COLUMN_MULTIPLECHOICE_NAME, nullable = false)
  private Boolean multipleChoice = false;

  @ElementCollection
  @CollectionTable(name = "poll_pollChoice", joinColumns = @JoinColumn(name = "owner_id"))
  private List<PollChoice> pollChoice = new ArrayList<>();

  @Column(name = DBConstant.POLL_COLUMN_CREATED_NAME, nullable = false)
  private OffsetDateTime created;
}
