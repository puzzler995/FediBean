package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}
