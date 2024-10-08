package cl.franciscosolis.rubybox.services.user;

import cl.franciscosolis.rubybox.models.abilities.AssignedRole;
import cl.franciscosolis.rubybox.models.abilities.Permission;
import cl.franciscosolis.rubybox.models.abilities.Role;
import cl.franciscosolis.rubybox.models.abilities.UserAssignedPermission;
import cl.franciscosolis.rubybox.models.user.User;
import cl.franciscosolis.rubybox.repositories.UsersRepository;
import cl.franciscosolis.rubybox.repositories.abilities.AssignedRolesRepository;
import cl.franciscosolis.rubybox.repositories.abilities.UserAssignedPermissionsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AssignedRolesRepository assignedRolesRepository;
    private final UserAssignedPermissionsRepository userAssignedPermissionsRepository;

    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, AssignedRolesRepository assignedRolesRepository, UserAssignedPermissionsRepository userAssignedPermissionsRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.assignedRolesRepository = assignedRolesRepository;
        this.userAssignedPermissionsRepository = userAssignedPermissionsRepository;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public UserDetails loadUserByUsername(String email) {
        User user = this.usersRepository.findUserByEmailAndDeletedAtIsNull(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }

    public void softDeleteUser(UUID userId) {
        User user = usersRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
        user.setDeletedAt(LocalDateTime.now());
        usersRepository.save(user);
    }

    public void assignRolesToUser(User user, Role... roles) {
        for (Role role : roles) {
            this.assignedRolesRepository.findAssignedRoleByRoleIdAndUserId(role.getId(), user.getId()).orElseGet(() -> this.assignedRolesRepository.save(new AssignedRole().setRole(role).setUser(user)));
        }
    }

    public void assignPermissionsToUser(User user, Permission... permissions) {
        for (Permission permission : permissions) {
            this.userAssignedPermissionsRepository.findUserAssignedPermissionByPermissionIdAndUserId(permission.getId(), user.getId()).orElseGet(() -> this.userAssignedPermissionsRepository.save(new UserAssignedPermission().setPermission(permission).setUser(user)));
        }
    }

}