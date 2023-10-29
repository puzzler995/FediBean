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
public class AssetProperty implements Serializable {
  private static final long serialVersionUID = -8837849667034501904L;

  @Column(name = DBConstant.ASSETPROPERTY_COLUMN_NAME_NAME1, nullable = false)
  private String name;

  @Column(name = DBConstant.ASSETPROPERTY_COLUMN_VALUE_NAME1, nullable = false)
  private Object value;
}
