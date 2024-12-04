package ITProject.example.WebSelling.dto.request.ProductRequets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CategoryRequest {

    @JsonProperty("name")
    String categoryName;

    @JsonProperty("description")
    String categoryDescription;
}
