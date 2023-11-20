package dev.puzzler995.fedibean.data.graph.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * DTO for {@link dev.puzzler995.fedibean.data.graph.model.node.UserNode} and {@link
 * dev.puzzler995.fedibean.data.graph.model.node.UserProfileNode}
 */
@Getter
@Builder
@Slf4j
public class UserAccountDto implements UserDetails {
  @Serial private static final long serialVersionUID = -2831783043262209242L;
  private String email;
  private String id;
  private boolean isAccountNonExpired;
  private boolean isAccountNonLocked;
  private boolean isCredentialsNonExpired;
  private boolean isEnabled;
  private String password;
  private String username;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }
}
