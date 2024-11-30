package ITProject.example.WebSelling.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long productId;

    String productName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false)
    Set<Specification> specifications = new HashSet<>();

    @Column(name = "state")
    Integer status = 1;

    String description;

    String thumbnail;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    List<ProductImage> productImages;

}
