package ITProject.example.WebSelling.dto.response;

import ITProject.example.WebSelling.entity.*;
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


    Set<Specification> specifications;

    Set<DetailDescription> detailDescriptions;

    String description;

    String thumbnail;

    List<ProductImage> productImages;
}
