package ITProject.example.WebSelling.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ShippingMethodRequest {

    String shippingName;

    String description;
}
