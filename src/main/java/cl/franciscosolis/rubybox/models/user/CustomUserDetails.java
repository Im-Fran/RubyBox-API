package cl.franciscosolis.rubybox.models.user;

import cl.franciscosolis.rubybox.models.abilities.Permission;
import cl.franciscosolis.rubybox.models.abilities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final User user;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public CustomUserDetails(User user){
        super();
        this.user = user;

        for(Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

            for(Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        for(Permission permission : user.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return user.getDeletedAt() == null;
    }
}
