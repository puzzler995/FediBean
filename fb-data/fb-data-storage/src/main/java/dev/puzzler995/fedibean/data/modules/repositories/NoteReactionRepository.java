package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.NoteReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteReactionRepository extends JpaRepository<NoteReaction, UUID> {}
