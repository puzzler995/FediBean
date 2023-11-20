package dev.puzzler995.fedibean.data.graph.modules.repository;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbuseReportRepository
    extends ReactiveNeo4jRepository<AbuseReportRepository, String> {}
