package dev.puzzler995.fedibean.data.storage.modules.repository;

import dev.puzzler995.fedibean.data.storage.model.Artifact;
import dev.puzzler995.fedibean.data.storage.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, UUID> {
  User findByUsername(String username);

  @Transactional
  @Modifying
  @Query("update User u set u.avatar = ?1 where u.id = ?2")
  int updateAvatarById(Artifact avatar, @NonNull UUID id);

  @Transactional
  @Modifying
  @Query("update User u set u.displayName = ?1 where u.id = ?2")
  int updateDisplayNameById(@NonNull String displayName, @NonNull UUID id);
}
