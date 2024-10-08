package cl.franciscosolis.rubybox.configuration;

import cl.franciscosolis.rubybox.models.Product;
import cl.franciscosolis.rubybox.models.abilities.Permission;
import cl.franciscosolis.rubybox.models.abilities.Role;
import cl.franciscosolis.rubybox.models.user.User;
import cl.franciscosolis.rubybox.repositories.abilities.PermissionsRepository;
import cl.franciscosolis.rubybox.repositories.ProductsRepository;
import cl.franciscosolis.rubybox.repositories.abilities.RolesRepository;
import cl.franciscosolis.rubybox.repositories.UsersRepository;
import cl.franciscosolis.rubybox.services.user.UserService;
import cl.franciscosolis.rubybox.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration(exclude= ErrorMvcAutoConfiguration.class)
public class Configuration {

    private static final Logger log = LoggerFactory.getLogger(Configuration.class);

    @Bean
    CommandLineRunner initDatabase(
            UserService userService,
            UsersRepository usersRepository,

            PermissionsRepository permissionsRepository,
            RolesRepository rolesRepository,

            ProductsRepository productsRepository
    ) {
        return args -> {
            Product product = new Product()
                    .setId(7803525000240L)
                    .setName("Brownie receta original")
                    .setPrice(700.0);
            productsRepository.save(product);

            User user = usersRepository.findUserByEmailAndDeletedAtIsNull("jdoe@rubybox.cl").orElseGet(() -> userService.createUser(new User().setName("John Doe").setEmail("jdoe@rubybox.cl").setPassword("password")));
            log.info("Preloading {}", productsRepository.findById(product.getId()).orElseThrow());
            log.info("Preloading {}", usersRepository.findUserByEmailAndDeletedAtIsNull(user.getEmail()).orElseThrow());


            // Crea los permisos y roles por defecto
            Arrays.asList(
                    /* /admin/users */
                    Pair.of("admin.users", Pair.of("Administrar Usuarios", "Permite administrar usuarios de Ruby Box")),
                    Pair.of("admin.users.update", Pair.of("Administrar Usuarios - Actualizar Datos", "Permite actualizar los datos de un usuario de Ruby Box")),
                    Pair.of("admin.users.reset-password", Pair.of("Administrar Usuarios - Resetear Contraseña", "Permite resetear la contraseña de un usuario de Ruby Box")),
                    Pair.of("admin.users.delete", Pair.of("Administrar Usuarios - Eliminar", "Permite marcar un usuario como eliminado de Ruby Box")),

                    /* /admin/business */
                    Pair.of("admin.business", Pair.of("Administrar Negocios", "Permite administrar negocios de Ruby Box")),
                    Pair.of("admin.business.create", Pair.of("Administrar Negocios - Crear", "Permite crear un negocio en Ruby Box")),
                    Pair.of("admin.business.update", Pair.of("Administrar Negocios - Actualizar Datos", "Permite actualizar los datos de un negocio en Ruby Box")),
                    Pair.of("admin.business.delete", Pair.of("Administrar Negocios - Eliminar", "Permite eliminar un negocio en Ruby Box")),

                    /* /admin/abilities/permissions */
                    Pair.of("admin.abilities.permissions", Pair.of("Administrar Habilidades - Permisos", "Permite administrar permisos de Ruby Box")),
                    Pair.of("admin.abilities.permissions.create", Pair.of("Administrar Habilidades - Permisos - Crear", "Permite crear permisos en Ruby Box")),
                    Pair.of("admin.abilities.permissions.manage", Pair.of("Administrar Habilidades - Permisos - Actualizar", "Permite actualizar permisos en Ruby Box")),
                    Pair.of("admin.abilities.permissions.delete", Pair.of("Administrar Habilidades - Permisos - Eliminar", "Permite eliminar permisos de Ruby Box")),

                    /* /admin/abilities/roles */
                    Pair.of("admin.abilities.roles", Pair.of("Administrar Habilidades - Roles", "Permite administrar roles de Ruby Box")),
                    Pair.of("admin.abilities.roles.create", Pair.of("Administrar Habilidades - Roles - Crear", "Permite crear roles en Ruby Box")),
                    Pair.of("admin.abilities.roles.manage", Pair.of("Administrar Habilidades - Roles - Actualizar", "Permite actualizar roles en Ruby Box")),
                    Pair.of("admin.abilities.roles.delete", Pair.of("Administrar Habilidades - Roles - Eliminar", "Permite eliminar roles de Ruby Box")),

                    /* /business */
                    Pair.of("business.create", Pair.of("Crear Negocio", "Permiso para crear un negocio")),
                    Pair.of("business.update", Pair.of("Actualizar Negocio", "Permiso para actualizar un negocio")),
                    Pair.of("business.delete", Pair.of("Eliminar Negocio", "Permiso para eliminar un negocio"))
            ).forEach(perm -> permissionsRepository.findPermissionByName(perm.k()).orElseGet(() -> permissionsRepository.save(new Permission().setName(perm.k()).setDisplay(perm.v().k()).setDescription(perm.v().v()))));

            Arrays.asList(
                    Pair.of("admin", Pair.of("Administrador", "Rol de administrador de Ruby Box")),
                    Pair.of("user", Pair.of("User", "Rol de usuario de Ruby Box"))
            ).forEach(role -> rolesRepository.findRoleByName(role.k()).orElseGet(() -> rolesRepository.save(new Role().setName(role.k()).setDisplay(role.v().k()).setDescription(role.v().v()))));

            user = usersRepository.findById(user.getId()).orElseThrow();
            if(user.getPermissions().isEmpty()) {
                userService.assignPermissionsToUser(user, permissionsRepository.findPermissionByName("admin.users.update").orElseThrow(), permissionsRepository.findPermissionByName("admin.users.reset-password").orElseThrow(), permissionsRepository.findPermissionByName("admin.users.delete").orElseThrow());
                userService.assignRolesToUser(user, rolesRepository.findRoleByName("admin").orElseThrow());
            }
        };
    }

}
