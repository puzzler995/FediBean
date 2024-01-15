package dev.puzzler995.fedibean.data.modules.repository;

import dev.puzzler995.fedibean.data.model.UserEntity;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, UUID> {
  UserEntity findByUsername(String username);
}
