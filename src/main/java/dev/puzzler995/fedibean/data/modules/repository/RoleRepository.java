package dev.puzzler995.fedibean.data.modules.repository;

import dev.puzzler995.fedibean.data.model.RoleEntity;
import dev.puzzler995.fedibean.data.model.UserEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends Neo4jRepository<RoleEntity, String> {
  @Query(
      "MATCH (u:User)-[:REGISTERED_AS]->(l:LocalUser)-[:HAS_ROLE]->(r:Role) WHERE u.username = $username RETURN r")
  List<RoleEntity> findAllByUsername(@Param("username") String username);

  @Query(
      "MATCH (u:User)-[:REGISTERED_AS]->(l:LocalUser)-[:HAS_ROLE]->(r:Role) WHERE r.name = $name RETURN u")
  List<UserEntity> findAllUsersByRoleName(@Param("name") String name);

  @Query("MATCH (l:LocalUser)-[:HAS_ROLE]->(r:Role) WHERE l.id = $id RETURN r")
  List<RoleEntity> findAllByLocalUsersId(UUID id);

  RoleEntity findByName(String name);
}
