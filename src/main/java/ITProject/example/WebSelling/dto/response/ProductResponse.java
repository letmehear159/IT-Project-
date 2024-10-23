package ITProject.example.WebSelling.dto.response;

import ITProject.example.WebSelling.entity.Category;
import ITProject.example.WebSelling.entity.Manufacturer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ProductResponse {

    Long productId;


    String productName;

    Category category;

    float price;

    Manufacturer manufacturer;

    Map<String, String> specifications;

    String description;

    String thumbnail;
}
