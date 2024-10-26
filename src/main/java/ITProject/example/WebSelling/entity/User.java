package ITProject.example.WebSelling.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    List<Order> orders;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    ShoppingCart shoppingCart;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    //Không có nullable = false thì không thể tự động tạo được một customerShipping mới
    //Trong sql và sẽ không tự động map foreign key cho mình
    List<CustomerShipping> customerShipping;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", state=" + state +
                // Avoid including full details of orders, roles, shoppingCart, and customerShipping to prevent recursion
                ", ordersCount=" + (orders != null ? orders.size() : 0) +
                ", rolesCount=" + (roles != null ? roles.size() : 0) +
                ", shoppingCartId=" + (shoppingCart != null ? shoppingCart.getCartId() : "null") +
                ", customerShippingCount=" + (customerShipping != null ? customerShipping.size() : 0) +
                '}';


    }
}


