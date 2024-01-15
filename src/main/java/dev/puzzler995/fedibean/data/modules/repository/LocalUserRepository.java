package dev.puzzler995.fedibean.data.modules.repository;

import dev.puzzler995.fedibean.data.model.LocalUserEntity;
import dev.puzzler995.fedibean.data.model.RoleEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface LocalUserRepository extends Neo4jRepository<LocalUserEntity, UUID> {

  @Query(
      "MATCH (u:User)-[:REGISTERED_AS]->(l:LocalUser)-[p:HAS_ROLE*0..]->(r:Role) WHERE u.username = $username RETURN l, collect(r) as roles, u as user")
  LocalUserEntity findByUsername(@Param("username") String username);

  @Query(
      "MATCH (u:User)-[:REGISTERED_AS]->(:LocalUser)-[:AUTHENTICATED_BY]->(p:Password) WHERE u.username = $username RETURN p.password")
  String findPasswordByUsername(@Param("username") String username);

  @Query("MATCH (l:LocalUser)-[:HAS_ROLE*0..]->(r:Role) WHERE l.id = $id RETURN collect(r)")
  List<RoleEntity> findAllRolesById(UUID id);
}
