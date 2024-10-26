package ITProject.example.WebSelling.dto.request;

import ITProject.example.WebSelling.entity.OrderDetail;
import ITProject.example.WebSelling.entity.Voucher;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Phần quan trọng nhất, để làm sau
public class OrderRequest {
    Long userId;

    String customerName;

    String shippingAddress;

    //Dựa vào shipping Method mà random kết quả.
//    LocalDate receiveDate;

    String paymentMethod;

    String shippingMethod;

    String phoneNumber;

    String note;

    List<Long> orderDetailIds;

}
