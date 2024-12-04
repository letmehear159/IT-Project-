package ITProject.example.WebSelling.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor()
@AllArgsConstructor()
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "detail_description")
public class DetailDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long Id;

    @Column(name = "title")
    String title;

    @JsonProperty("detail_content")
    @Column(name = "detail_content", length = 2000)
    String detailContent;

    @Column(name = "order_content")
    Long order;

}
