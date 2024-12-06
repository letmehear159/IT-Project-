package ITProject.example.WebSelling.repository;

import ITProject.example.WebSelling.entity.OrderDetail;
import ITProject.example.WebSelling.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByShoppingCart(ShoppingCart shoppingCart);

}
