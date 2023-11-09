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
public class Attachment implements Serializable {
  private static final long serialVersionUID = -6734095984162321L;

  @Column(name = DBConstant.ATTACHMENT_COLUMN_FILEID_NAME, nullable = false)
  private String fileId;

  @Column(name = DBConstant.ATTACHMENT_COLUMN_FILETYPE_NAME, nullable = false)
  private String fileType;
}
