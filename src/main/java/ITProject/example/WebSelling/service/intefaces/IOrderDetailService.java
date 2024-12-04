package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.OrderProductRequest.OrderDetailRequest;
import ITProject.example.WebSelling.dto.request.OrderProductRequest.OrderDetailVoucherRequest;
import ITProject.example.WebSelling.dto.response.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailResponse getOrderDetail(Long orderDetailId);

    OrderDetailResponse saveOrderDetail(OrderDetailRequest orderDetailRequest);

    OrderDetailResponse updateOrderDetail(OrderDetailVoucherRequest orderDetailRequest);

    void deleteOrderDetail(Long orderDetailId);

    List<OrderDetailResponse> getAllOrderDetails();

    List<OrderDetailResponse> updateOrderDetails(List<OrderDetailVoucherRequest> orderDetailVoucherRequests);

}
