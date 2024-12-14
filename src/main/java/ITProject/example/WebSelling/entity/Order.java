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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    Long orderId;

    @Column(name = "customer_name", nullable = false)
    String customerName;

    @Column(name = "shipping_address", nullable = false)
    String shippingAddress;

    @Column(name = "order_date")
    LocalDate orderDate;

    @Column(name = "receive_date")
    LocalDate receiveDate;

    @Column(name = "shipping_state", nullable = false)
    //Trạng thái giao hàng
    String shippingStatus;

    @Column(name = "payment_method", nullable = false)
    //Phương thức thanh toán
    String paymentMethod;

    @Column(name = "payment_status", nullable = false)
    //Trạng thái thanh toán
    String paymentStatus;

    @Column(name = "shipping_method", nullable = false)
    //Phương thức giao hàng
    String shippingMethod;

    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    String note;

    @Column(name = "total_price")
    float totalPrice;

    @PrePersist
    public void onCreate() {
        orderDate = LocalDate.now();
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;
}
