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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "order_state", nullable = false)
    String orderStatus;

    @Column(name = "shipping_state", nullable = false)
    String shippingStatus;

    @Column(name = "payment_method", nullable = false)
    String paymentMethod;

    @Column(name = "payment_status", nullable = false)
    String paymentStatus;

    @Column(name = "shipping_method", nullable = false)
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

    @OneToMany
    @JoinColumn(name = "order_id")
    List<OrderDetail> orderDetails;

}
