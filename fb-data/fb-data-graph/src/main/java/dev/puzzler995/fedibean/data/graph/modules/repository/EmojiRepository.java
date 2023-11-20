package dev.puzzler995.fedibean.data.graph.modules.repository;

import dev.puzzler995.fedibean.data.graph.model.node.EmojiNode;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmojiRepository extends ReactiveNeo4jRepository<EmojiNode, String> {
  Mono<EmojiNode> findByName(String name);
}
