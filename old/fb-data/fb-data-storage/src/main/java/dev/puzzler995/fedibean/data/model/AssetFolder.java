package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.ASSETFOLDER_TABLE_NAME)
public class AssetFolder extends DBItem {
  private static final long serialVersionUID = 7109080277225968210L;
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "creator_id")
  private User creator;
  @Column(name = DBConstant.ASSETFOLDER_COLUMN_NAME_NAME, nullable = false)
  private String name;
  @ManyToOne
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "parent_id")
  private AssetFolder parent;
}
