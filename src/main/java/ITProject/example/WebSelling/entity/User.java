package ITProject.example.WebSelling.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    List<Order> orders;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    List<Notification> notifications;
//
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    List<CustomerShipping> customerShippings;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<Role> roles;

    @OneToOne
    @JoinColumn(name = "user_id")
    ShoppingCart shoppingCart;

}
