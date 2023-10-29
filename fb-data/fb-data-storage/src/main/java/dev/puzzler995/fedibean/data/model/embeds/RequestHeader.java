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
public class RequestHeader implements Serializable {
  private static final long serialVersionUID = 5045247940526837024L;

  @Column(name = DBConstant.REQUESTHEADER_COLUMN_NAME_NAME, nullable = false)
  private String name;

  @Column(name = DBConstant.REQUESTHEADER_COLUMN_VALUE_NAME, nullable = false)
  private Object value;
}
