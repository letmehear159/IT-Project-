package ITProject.example.WebSelling.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    Long id;

    @Column(name = "user_name")
    String username;

    String password;

    @Column(name = "full_name")
    String fullName;

    String email;

    @Column(name = "phone_number")
    String phone;

    LocalDate dob;

    Integer state;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Order> orders;

}
