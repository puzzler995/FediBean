package dev.puzzler995.fedibean.data.modules.service;

import dev.puzzler995.fedibean.data.model.LocalUserEntity;
import dev.puzzler995.fedibean.data.model.LocalUserEntity.AUTHENTICATION_TYPE;
import dev.puzzler995.fedibean.data.model.PasswordEntity;
import dev.puzzler995.fedibean.data.model.RoleEntity;
import dev.puzzler995.fedibean.data.model.UserEntity;
import dev.puzzler995.fedibean.data.model.dto.LocalUserDTO;
import dev.puzzler995.fedibean.data.model.dto.RoleDTO;
import dev.puzzler995.fedibean.data.modules.repository.LocalUserRepository;
import dev.puzzler995.fedibean.data.modules.repository.PasswordRepository;
import dev.puzzler995.fedibean.data.modules.repository.RoleRepository;
import dev.puzzler995.fedibean.data.modules.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final LocalUserRepository localUserRepository;
  private final PasswordRepository passwordRepository;
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  @Autowired
  public UserService(
      UserRepository userRepository,
      LocalUserRepository localUserRepository,
      PasswordRepository passwordRepository,
      RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.localUserRepository = localUserRepository;
    this.passwordRepository = passwordRepository;
    this.roleRepository = roleRepository;
  }

  public void resetDatabase() {
    localUserRepository.deleteAll();
    passwordRepository.deleteAll();
    roleRepository.deleteAll();
    userRepository.deleteAll();
  }

  public void createUserWithAuthentication(String username, String password) {
    UserEntity user = UserEntity.builder().id(null).username(username).build();
    var role = roleRepository.save(RoleEntity.builder().name("ROLE_ADMIN").build());
    user = userRepository.save(user);
    var localUser =
        localUserRepository.save(
            LocalUserEntity.builder().user(user).isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).roles(List.of(role)).authenticationType(AUTHENTICATION_TYPE.PASSWORD).build());
    passwordRepository.save(PasswordEntity.builder().id(null).password(password).user(localUser).build());
  }

  public LocalUserDTO getLocalUserDtoByUsername(String username) {
    var localUserDTOBuilder = LocalUserDTO.builder();
    var user = userRepository.findByUsername(username);
    LocalUserEntity localUser = localUserRepository.findByUsername(username);
    var password = localUserRepository.findPasswordByUsername(username);
    var roles = roleRepository.findAllByLocalUsersId(localUser.getId());
    if ((user == null) || (localUser == null) || (password == null) || (roles == null)) {
      throw new UsernameNotFoundException("User not found");
    }
    localUserDTOBuilder
        .username(user.getUsername())
        .authorities(
            roles.stream().map(roleEntity -> getRoleDtoByRoleName(roleEntity.getName())).toList())
        .isAccountNonExpired(localUser.isAccountNonExpired())
        .isAccountNonLocked(localUser.isAccountNonLocked())
        .isCredentialsNonExpired(localUser.isCredentialsNonExpired())
        .isEnabled(localUser.isEnabled())
        .password(password);
    return localUserDTOBuilder.build();
  }

  public GrantedAuthority getRoleDtoByRoleName(String name) {
    return RoleDTO.builder().name(name).build();
  }
}
