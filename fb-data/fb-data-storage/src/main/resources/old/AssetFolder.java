package old;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "asset_folder")
public class AssetFolder extends DBEntity implements Serializable {
    private static final long serialVersionUID = -4425528262555060756L;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @CreatedBy
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private AssetFolder parent;

    @OneToMany(mappedBy = "assetFolder", orphanRemoval = true)
    private Set<Asset> assets = new LinkedHashSet<>();

}