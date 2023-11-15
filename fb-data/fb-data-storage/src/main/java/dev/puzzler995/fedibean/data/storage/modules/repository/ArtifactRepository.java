package dev.puzzler995.fedibean.data.storage.modules.repository;

import dev.puzzler995.fedibean.data.storage.model.Artifact;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactRepository extends JpaRepository<Artifact, UUID> {}
