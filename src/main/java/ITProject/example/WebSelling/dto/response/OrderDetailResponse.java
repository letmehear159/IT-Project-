package ITProject.example.WebSelling.dto.response;

import ITProject.example.WebSelling.entity.Product;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OrderDetailResponse {
    private Long orderDetailId;


    Product product;

    float price;

    int quantity;

    float totalPrice;
}
