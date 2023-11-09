package dev.puzzler995.fedibean.data.model.embeds;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RemoteUserReference implements Serializable {
  private static final long serialVersionUID = 4885018018539853455L;
  @Column(name = DBConstant.REMOTEUSERREFERENCE_COLUMN_HOST_NAME, nullable = false)
  private String host;
  @Column(name = DBConstant.REMOTEUSERREFERENCE_COLUMN_URI_NAME, nullable = false)
  private URI uri;
  @Column(name = DBConstant.REMOTEUSERREFERENCE_COLUMN_URL_NAME)
  private URL url;
  @Column(name = DBConstant.REMOTEUSERREFERENCE_COLUMN_USERNAME_NAME, nullable = false)
  private String username;
}
