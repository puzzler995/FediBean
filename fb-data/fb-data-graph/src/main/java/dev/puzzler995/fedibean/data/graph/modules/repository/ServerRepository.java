package dev.puzzler995.fedibean.data.graph.modules.repository;

import dev.puzzler995.fedibean.data.graph.model.node.ServerNode;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ServerRepository extends ReactiveNeo4jRepository<ServerNode, String> {
  Mono<ServerNode> findByHost(String host);

  Mono<ServerNode> findByName(String name);
}
