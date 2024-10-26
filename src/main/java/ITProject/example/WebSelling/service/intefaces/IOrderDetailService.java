package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.OrderDetailRequest;
import ITProject.example.WebSelling.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail getOrderDetail(Long orderDetailId);

    OrderDetail saveOrderDetail(OrderDetailRequest orderDetailRequest);

    OrderDetail updateOrderDetail(OrderDetailRequest orderDetailRequest, Long orderDetailId);

    void deleteOrderDetail(Long orderDetailId);

    List<OrderDetail> getAllOrderDetails();



}
