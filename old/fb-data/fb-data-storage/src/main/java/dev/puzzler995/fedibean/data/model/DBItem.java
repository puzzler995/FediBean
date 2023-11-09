package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class DBItem implements Serializable {
  private static final long serialVersionUID = -1100343759994222415L;
  @CreatedDate
  @Column(name = DBConstant.DBITEM_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = DBConstant.DBITEM_COLUMN_ID_NAME, nullable = false)
  private UUID id;
}
