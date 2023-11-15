package dev.puzzler995.fedibean.data.storage.modules.repository;

import dev.puzzler995.fedibean.data.storage.model.Post;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

public interface PostRepository extends PagingAndSortingRepository<Post, UUID> {
  List<Post> findByPostedBy_IdAndReplyToNullOrderByCreatedAtDesc(
      @NonNull UUID id, Pageable pageable);

  List<Post> findByPostedBy_IdOrderByCreatedAtDesc(@NonNull UUID id, Pageable pageable);
}
