package dev.puzzler995.fedibean;

import dev.puzzler995.fedibean.data.modules.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitializationService {
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserService userService;

  @Autowired
  public InitializationService(
      UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PostConstruct
  public void init() {
    userService.resetDatabase();
    userService.createUserWithAuthentication("admin", bCryptPasswordEncoder.encode("admin"));
    userService.createUserWithAuthentication("user", bCryptPasswordEncoder.encode("user"));
  }
}
