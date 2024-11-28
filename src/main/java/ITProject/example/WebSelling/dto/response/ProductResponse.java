package ITProject.example.WebSelling.dto.response;

import ITProject.example.WebSelling.entity.Category;
import ITProject.example.WebSelling.entity.Manufacturer;
import ITProject.example.WebSelling.entity.Specification;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

//    Map<String, String> specifications;

    Set<Specification> specifications;

    String description;

    String thumbnail;
}
