package cl.franciscosolis.rubybox.repositories.abilities;

import cl.franciscosolis.rubybox.models.abilities.AssignedRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssignedRolesRepository extends JpaRepository<AssignedRole, UUID> {

    Optional<AssignedRole> findAssignedRoleByRoleIdAndUserId(UUID roleId, UUID userId);
}
