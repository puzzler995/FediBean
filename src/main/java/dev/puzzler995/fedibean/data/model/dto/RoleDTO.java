package dev.puzzler995.fedibean.data.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
public class RoleDTO implements GrantedAuthority {
  private String name;

  @Override
  public String getAuthority() {
    return name;
  }
}
