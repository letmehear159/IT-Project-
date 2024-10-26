package ITProject.example.WebSelling.repository;

import ITProject.example.WebSelling.entity.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Long> {

}
