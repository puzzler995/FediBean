package dev.puzzler995.fedibean.data.storage.modules.repository;

import dev.puzzler995.fedibean.data.storage.model.PostReaction;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface PostReactionRepository extends JpaRepository<PostReaction, UUID> {
  long countDistinctByPost_Id(@NonNull UUID id);

  List<PostReaction> findByPost_Id(@NonNull UUID id, Sort sort);
}
