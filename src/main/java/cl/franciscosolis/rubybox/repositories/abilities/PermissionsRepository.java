package cl.franciscosolis.rubybox.repositories.abilities;

import cl.franciscosolis.rubybox.models.abilities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PermissionsRepository extends JpaRepository<Permission, UUID> {

    Optional<Permission> findPermissionByName(String name);

    Optional<Permission> findPermissionByDisplay(String display);
}
