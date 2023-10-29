package dev.puzzler995.fedibean.data.model.embeds;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class NoteReactionType implements Serializable {
  private static final long serialVersionUID = -8802019744271307962L;

  @Column(name = DBConstant.NOTEREACTION_COLUMN_EMOJITEXT_NAME, nullable = false)
  private String emojiText;

  @Column(name = DBConstant.NOTEREACTION_COLUMN_COUNT_NAME, nullable = false)
  private Short count;
}
