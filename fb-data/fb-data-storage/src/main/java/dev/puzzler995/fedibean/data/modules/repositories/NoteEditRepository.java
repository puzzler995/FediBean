package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.NoteEdit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteEditRepository extends JpaRepository<NoteEdit, UUID> {}
