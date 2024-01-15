package dev.puzzler995.fedibean.data.modules.repository;

import dev.puzzler995.fedibean.data.model.PostEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends Neo4jRepository<PostEntity, UUID> {
  @Query("MATCH (u:User)-[:POSTED]->(p:Post) WHERE u.username = $username RETURN p")
  List<PostEntity> getAllByAuthor(@Param("username") String username);

  PostEntity getById(UUID id);
}
