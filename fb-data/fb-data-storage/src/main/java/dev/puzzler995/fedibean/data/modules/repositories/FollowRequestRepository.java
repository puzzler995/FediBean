package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.FollowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowRequestRepository extends JpaRepository<FollowRequest, UUID> {}
