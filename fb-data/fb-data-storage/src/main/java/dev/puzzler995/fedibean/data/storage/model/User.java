package dev.puzzler995.fedibean.data.storage.model;

import dev.puzzler995.fedibean.data.storage.model.constant.DatabaseConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = DatabaseConstant.USER_TABLE_NAME)
public class User implements Serializable {
  @Serial private static final long serialVersionUID = 5026069459160815420L;
  @OneToOne(orphanRemoval = true)
  @JoinColumn(name = "avatar_id")
  private Artifact avatar;
  @Column(name = DatabaseConstant.USER_COLUMN_DISPLAYNAME_NAME)
  private String displayName;
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = DatabaseConstant.USER_COLUMN_ID_NAME, nullable = false)
  private UUID id;
  @Column(name = DatabaseConstant.USER_COLUMN_USERNAME_NAME, nullable = false)
  private String username;
}
