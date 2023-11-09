package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.NoteEdit;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteEditRepository extends JpaRepository<NoteEdit, UUID> {}
