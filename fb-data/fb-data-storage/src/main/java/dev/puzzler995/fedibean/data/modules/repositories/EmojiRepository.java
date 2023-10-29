package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmojiRepository extends JpaRepository<Emoji, UUID> {}
