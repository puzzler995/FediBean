package dev.puzzler995.fedibean.data.storage.modules.repository;

import dev.puzzler995.fedibean.data.storage.model.User;
import dev.puzzler995.fedibean.data.storage.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, User> {

  UserAccount findByUserobj_UsernameOrEmail(String username, String email);
}
