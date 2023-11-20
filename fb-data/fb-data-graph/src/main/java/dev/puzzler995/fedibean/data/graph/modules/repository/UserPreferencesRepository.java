package dev.puzzler995.fedibean.data.graph.modules.repository;

import dev.puzzler995.fedibean.data.graph.model.node.UserPreferencesNode;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferencesRepository
    extends ReactiveNeo4jRepository<UserPreferencesNode, String> {}
