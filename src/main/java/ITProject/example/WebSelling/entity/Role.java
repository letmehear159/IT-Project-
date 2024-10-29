package ITProject.example.WebSelling.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    String roleId;

    String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    Set<Permission> permissions;
}
