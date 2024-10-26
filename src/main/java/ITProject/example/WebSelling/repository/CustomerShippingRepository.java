package ITProject.example.WebSelling.repository;

import ITProject.example.WebSelling.entity.CustomerShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerShippingRepository extends JpaRepository<CustomerShipping, Long> {


}
