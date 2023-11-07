package old;

import dev.puzzler995.fedibean.data.model.Asset;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "announcement")
public class Announcement extends DBEntity implements Serializable {
  private static final long serialVersionUID = 8364253152110458587L;
  @ManyToOne
  @JoinColumn(name = "attachment_id")
  private Asset attachment;
  @Column(name = "show_popup", nullable = false)
  private Boolean showPopup = false;
  @Lob
  @Column(name = "text", nullable = false)
  private String text;
  @Lob
  @Column(name = "title", nullable = false)
  private String title;
  @LastModifiedDate
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;
}
