package ITProject.example.WebSelling.repository;

import ITProject.example.WebSelling.entity.Order;
import ITProject.example.WebSelling.entity.OrderDetail;
import ITProject.example.WebSelling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);


}
