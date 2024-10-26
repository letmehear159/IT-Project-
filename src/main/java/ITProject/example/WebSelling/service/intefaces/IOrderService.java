package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.OrderRequest;
import ITProject.example.WebSelling.entity.Order;

public interface IOrderService {

    Order createOrder(OrderRequest orderRequest);



}
