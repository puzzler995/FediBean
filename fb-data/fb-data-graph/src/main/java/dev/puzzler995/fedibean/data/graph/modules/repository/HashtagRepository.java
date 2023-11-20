package dev.puzzler995.fedibean.data.graph.modules.repository;

import dev.puzzler995.fedibean.data.graph.model.node.HashtagNode;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends ReactiveNeo4jRepository<HashtagNode, String> {}
