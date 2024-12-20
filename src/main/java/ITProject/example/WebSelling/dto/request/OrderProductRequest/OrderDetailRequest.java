package ITProject.example.WebSelling.dto.request.OrderProductRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OrderDetailRequest {

    Long productId;

    Long cartId;

    //Giá tiền lấy từ trong product.
    //Số lượng lúc truyền vào mặc định là 1
    //Lần đầu tiên thêm vào giỏ hàng để trống quantity rồi ở service set nó là 1
    int quantity;




}
