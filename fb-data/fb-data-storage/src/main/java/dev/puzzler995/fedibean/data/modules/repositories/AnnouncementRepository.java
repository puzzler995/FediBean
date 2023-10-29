package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnnouncementRepository extends JpaRepository<Announcement, UUID> {}
