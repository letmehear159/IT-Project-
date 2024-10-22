package ITProject.example.WebSelling.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voucher_id")
    Long voucherId;

    @Column(name = "percent_discount")
    float percentage;

    @Column(name = "maximum_discount_money")
    float maximumMoney;

    @Column(name = "number_left")
    int numberVoucherLeft;


    @Column(name = "state")
    int status;

}
