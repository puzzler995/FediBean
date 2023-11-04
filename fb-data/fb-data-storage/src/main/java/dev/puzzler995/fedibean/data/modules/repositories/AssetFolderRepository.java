package dev.puzzler995.fedibean.data.modules.repositories;

import dev.puzzler995.fedibean.data.model.AssetFolder;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetFolderRepository extends JpaRepository<AssetFolder, UUID> {}
