package cl.franciscosolis.rubybox.repositories;

import cl.franciscosolis.rubybox.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {

    /* Encuentra a un usuario por correo */
    Optional<User> findUserByEmailAndDeletedAtIsNull(String email);

    boolean existsUserByEmail(String email);
}
