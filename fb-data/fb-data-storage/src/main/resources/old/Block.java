package old;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "block")
public class Block extends DBEntity {
  private static final long serialVersionUID = -8537115165593850954L;

  @CreatedBy
  @ManyToOne
  @JoinColumn(name = "blocker_id")
  private User blocker;

  @ManyToOne
  @JoinColumn(name = "blockee_id")
  private User blockee;

}
