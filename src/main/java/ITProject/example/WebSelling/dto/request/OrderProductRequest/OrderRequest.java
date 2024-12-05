package ITProject.example.WebSelling.dto.request.OrderProductRequest;

import lombok.*;

import java.util.List;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Phần quan trọng nhất, để làm sau
public class OrderRequest {
    String username;

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
