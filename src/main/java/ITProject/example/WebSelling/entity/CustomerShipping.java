package ITProject.example.WebSelling.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customer_shippings")
public class CustomerShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_ship_id")
    Long id;

    @Column(name = "receiver_name")
    String receiverName;


    @Column(name = "phone_number")
    String phoneNumber;

    String city;

    @Column(name = "detail_address")
    String detailAddress;


}
