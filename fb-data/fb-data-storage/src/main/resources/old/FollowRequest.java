package old;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "follow_request")
public class FollowRequest extends DBEntity {
  private static final long serialVersionUID = 7435679376018634655L;
  @ManyToOne(optional = false)
  @JoinColumn(name = "requester_id", nullable = false)
  private User requester;
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
