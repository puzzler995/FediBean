package dev.puzzler995.fedibean.data.modules.repository;

import dev.puzzler995.fedibean.data.model.PasswordEntity;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends Neo4jRepository<PasswordEntity, UUID> {}
