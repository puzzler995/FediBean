package dev.puzzler995.fedibean.data.model.embeds;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
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
public class PollChoice implements Serializable {
  private static final long serialVersionUID = -9156554630252515207L;

  @Lob
  @Column(name = DBConstant.POLLCHOICE_COLUMN_TEXT_NAME, nullable = false)
  private String text;

  @Column(name = DBConstant.POLLCHOICE_COLUMN_VOTES_NAME, nullable = false)
  private Integer votes;

  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = DBConstant.POLLCHOICE_COLUMN_ID_NAME, nullable = false)
  private Integer id;
}
