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
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = DatabaseConstant.USERACCOUNT_TABLE_NAME)
public class UserAccount { // extends UserDetails {
  @Serial private static final long serialVersionUID = 1087521999392088838L;
  @Column(name = DatabaseConstant.USERACCOUNT_COLUMN_ACCOUNTNONEXPIRED_NAME, nullable = false)
  private boolean accountNonExpired;
  @Column(name = DatabaseConstant.USERACCOUNT_COLUMN_ACCOUNTNONLOCKED_NAME, nullable = false)
  private boolean accountNonLocked;
  @Column(name = DatabaseConstant.USERACCOUNT_COLUMN_CREDENTIALSNONEXPIRED_NAME, nullable = false)
  private boolean credentialsNonExpired;
  @Column(name = DatabaseConstant.USERACCOUNT_COLUMN_EMAIL_NAME)
  private String email;
  @Column(name = DatabaseConstant.USERACCOUNT_COLUMN_ENABLED_NAME, nullable = false)
  private boolean enabled;
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = DatabaseConstant.USERACCOUNT_COLUMN_ID_NAME, nullable = false)
  private UUID id;
  @Column(name = DatabaseConstant.USERACCOUNT_COLUMN_PASSWORD_NAME, nullable = false)
  private String password;
  @OneToOne(optional = false, orphanRemoval = true)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User userobj;

  // @Override
  // public Collection<? extends GrantedAuthority> getAuthorities() {
  //  return Collections.emptyList();
  // }
  //
  // @Override
  // public String getUsername() {
  //  return userobj.getUsername();
  // }

}
