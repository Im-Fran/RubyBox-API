package cl.franciscosolis.rubybox.models.abilities;

import cl.franciscosolis.rubybox.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "user_assigned_permissions")
@Table(name = "user_assigned_permissions")
@EntityListeners(AuditingEntityListener.class)
public class UserAssignedPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}