package dev.puzzler995.fedibean.authentication.modules;

import dev.puzzler995.fedibean.data.modules.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class DatabaseUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public DatabaseUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  public UserDetails loadUserByUsername(String username) {
    return userService.getLocalUserDtoByUsername(username);
  }
}
