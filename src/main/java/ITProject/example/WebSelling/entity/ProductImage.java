package ITProject.example.WebSelling.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_images")
public class ProductImage {
    public static final int MAXIMUM_IMAGES_PER_PRODUCT = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long productImageId;


    @Column(name = "image_url")
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    Product product;

    @Override
    public String toString() {
        return "ProductImage{" +
                "productImageId=" + productImageId +
                ", productId=" + product.getProductId() +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
