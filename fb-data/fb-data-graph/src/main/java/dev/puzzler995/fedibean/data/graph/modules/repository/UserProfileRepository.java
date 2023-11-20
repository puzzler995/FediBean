package dev.puzzler995.fedibean.data.graph.modules.repository;

import dev.puzzler995.fedibean.data.graph.model.node.UserProfileNode;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserProfileRepository extends ReactiveNeo4jRepository<UserProfileNode, String> {
  Mono<UserProfileNode> findByUser_Id(String id);
}
