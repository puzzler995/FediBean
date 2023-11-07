package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import dev.puzzler995.fedibean.data.model.embeds.Attachment;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = DBConstant.NOTEEDIT_TABLE_NAME)
public class NoteEdit extends DBItem {
  private static final long serialVersionUID = 3258297171306180553L;
  @ElementCollection
  @CollectionTable(name = "note_edit_attachments", joinColumns = @JoinColumn(name = "owner_id"))
  private List<Attachment> attachments = new ArrayList<>();
  @ManyToOne(optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "note_id", nullable = false)
  private Note note;
  @Lob
  @Column(name = DBConstant.NOTEEDIT_COLUMN_SUMMARY_NAME)
  private String summary;
  @Lob
  @Column(name = DBConstant.NOTEEDIT_COLUMN_TEXT_NAME)
  private String text;
}
