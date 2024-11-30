package ITProject.example.WebSelling.repository;

import ITProject.example.WebSelling.entity.Category;
import ITProject.example.WebSelling.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
