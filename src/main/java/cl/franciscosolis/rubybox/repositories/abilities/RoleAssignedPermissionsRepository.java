package cl.franciscosolis.rubybox.repositories.abilities;

import cl.franciscosolis.rubybox.models.abilities.RoleAssignedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleAssignedPermissionsRepository extends JpaRepository<RoleAssignedPermission, UUID> {

    Optional<RoleAssignedPermission> findRoleAssignedPermissionByRoleIdAndPermissionId(UUID roleId, UUID permissionId);
}
