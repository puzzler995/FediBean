package dev.puzzler995.fedibean.data.graph.modules.repository;

import dev.puzzler995.fedibean.data.graph.model.node.AssetNode;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends ReactiveNeo4jRepository<AssetNode, String> {}
