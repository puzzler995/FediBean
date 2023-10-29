package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.AbuseReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbuseReportRepository extends JpaRepository<AbuseReport, UUID> {}
