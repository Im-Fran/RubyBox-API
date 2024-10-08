package cl.franciscosolis.rubybox.services.roles;

import cl.franciscosolis.rubybox.models.abilities.Permission;
import cl.franciscosolis.rubybox.models.abilities.Role;
import cl.franciscosolis.rubybox.models.abilities.RoleAssignedPermission;
import cl.franciscosolis.rubybox.repositories.abilities.RoleAssignedPermissionsRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RoleAssignedPermissionsRepository roleAssignedPermissionsRepository;

    public RolesService(RoleAssignedPermissionsRepository roleAssignedPermissionsRepository) {
        this.roleAssignedPermissionsRepository = roleAssignedPermissionsRepository;
    }

    public void assignPermissionsToRole(Role role, Permission... permissions) {
        for (Permission permission : permissions) {
            this.roleAssignedPermissionsRepository.findRoleAssignedPermissionByRoleIdAndPermissionId(role.getId(), permission.getId()).orElseGet(() -> this.roleAssignedPermissionsRepository.save(new RoleAssignedPermission().setRole(role).setPermission(permission)));
        }
    }
}
