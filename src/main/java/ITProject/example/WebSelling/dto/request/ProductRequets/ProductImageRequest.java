package ITProject.example.WebSelling.dto.request.ProductRequets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageRequest {
    @JsonProperty("product_id")
    @Min(value = 1, message = "Product's ID must be > 0")
    Long productId;

    @Size(min = 5, max = 200, message = "Image's name")
    @JsonProperty("image_url")
    String imageUrl;
}
