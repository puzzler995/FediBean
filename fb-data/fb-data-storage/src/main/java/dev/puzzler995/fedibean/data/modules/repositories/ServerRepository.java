package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServerRepository extends JpaRepository<Server, UUID> {}
