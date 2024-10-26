package ITProject.example.WebSelling.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CustomerShippingRequest {

    String receiverName;

    String phoneNumber;

    String city;

    String detailAddress;

    Long userId;
}
