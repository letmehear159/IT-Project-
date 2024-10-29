package ITProject.example.WebSelling.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class VoucherRequest {
    float percentage;

    float maximumMoney;

    int numberVoucherLeft;

    int status;

    String voucherName;


}
