package ITProject.example.WebSelling.dto.request.OrderProductRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OrderDetailVoucherRequest {

    Long orderId;

    String voucherName;

    int quantity;
}
