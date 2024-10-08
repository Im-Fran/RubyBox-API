package cl.franciscosolis.rubybox.repositories.abilities;

import cl.franciscosolis.rubybox.models.abilities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RolesRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findRoleByName(String name);
}
