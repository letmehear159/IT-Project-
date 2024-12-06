package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.OrderProductRequest.OrderRequest;
import ITProject.example.WebSelling.entity.Order;

import java.util.List;

public interface IOrderService {

    Order createOrder(OrderRequest orderRequest);

    List<Order> getOrdersByUsername(String username);



}
