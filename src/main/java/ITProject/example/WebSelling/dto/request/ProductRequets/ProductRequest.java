package ITProject.example.WebSelling.dto.request.ProductRequets;

import ITProject.example.WebSelling.entity.DetailDescription;
import ITProject.example.WebSelling.entity.Specification;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ProductRequest {
    @JsonProperty("name")
    String productName;

    @JsonProperty("category")
    String categoryName;

    float price;

    @JsonProperty("manufacturer")
    String manufacturerName;

//    private Map<String, String> specifications;

    Set<Specification> specifications;

    @JsonProperty("detail_descriptions")
    Set<DetailDescription> detailDescriptions;

    String description;

}
