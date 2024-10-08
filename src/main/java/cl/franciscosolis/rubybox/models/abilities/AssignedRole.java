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
@Entity(name = "assigned_roles")
@Table(name = "assigned_roles")
@EntityListeners(AuditingEntityListener.class)
public class AssignedRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}