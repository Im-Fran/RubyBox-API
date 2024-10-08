package cl.franciscosolis.rubybox.repositories.abilities;

import cl.franciscosolis.rubybox.models.abilities.UserAssignedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAssignedPermissionsRepository extends JpaRepository<UserAssignedPermission, UUID> {

    Optional<UserAssignedPermission> findUserAssignedPermissionByPermissionIdAndUserId(UUID permissionId, UUID userId);
}
