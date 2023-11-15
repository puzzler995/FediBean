package dev.puzzler995.fedibean.data.storage.model;

import dev.puzzler995.fedibean.data.storage.model.constant.DatabaseConstant;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = DatabaseConstant.ARTIFACT_TABLE_NAME)
public class Artifact implements Serializable {
  private static final long serialVersionUID = 8426969720227965546L;

  @ElementCollection
  @CollectionTable(name = "artifact_attached_to", joinColumns = @JoinColumn(name = "artifact_id"))
  @Column(name = "post_id")
  private Set<UUID> attachedTo = new LinkedHashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = DatabaseConstant.ARTIFACT_COLUMN_ID_NAME, nullable = false)
  private UUID id;

  @Enumerated
  @Column(name = DatabaseConstant.ARTIFACT_COLUMN_TYPE_NAME, nullable = false)
  private ArtifactType type;

  @Column(name = DatabaseConstant.ARTIFACT_COLUMN_URI_NAME, nullable = false)
  private URI uri;

  @Override
  public final int hashCode() {
    if (this instanceof HibernateProxy hibernateProxy) {
      return hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode();
    } else {
      return getClass().hashCode();
    }
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Class<?> oEffectiveClass;
    if (o instanceof HibernateProxy hibernateProxy) {
      oEffectiveClass = hibernateProxy.getHibernateLazyInitializer().getPersistentClass();
    } else {
      oEffectiveClass = o.getClass();
    }
    Class<?> thisEffectiveClass;
    if (this instanceof HibernateProxy hibernateProxy) {
      thisEffectiveClass = hibernateProxy.getHibernateLazyInitializer().getPersistentClass();
    } else {
      thisEffectiveClass = getClass();
    }
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Artifact artifact = (Artifact) o;
    return getId() != null && Objects.equals(getId(), artifact.getId());
  }
}
