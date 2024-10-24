package ITProject.example.WebSelling.dto.request;

import ITProject.example.WebSelling.entity.Category;
import ITProject.example.WebSelling.entity.Manufacturer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

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

    private Map<String, String> specifications;

    String description;

}
