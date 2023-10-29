package old;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "announcement_read")
public class AnnouncementRead extends DBEntity implements Serializable {
    private static final long serialVersionUID = -7141417101435623457L;

    @CreatedBy
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "announcement_id", nullable = false)
    private Announcement announcement;

}