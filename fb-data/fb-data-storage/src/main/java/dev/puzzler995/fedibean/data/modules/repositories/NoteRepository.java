package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {}
