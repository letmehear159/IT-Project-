package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.OrderProductRequest.OrderRequest;
import ITProject.example.WebSelling.entity.Order;
import ITProject.example.WebSelling.entity.OrderDetail;
import ITProject.example.WebSelling.entity.User;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.OrderMapper;
import ITProject.example.WebSelling.repository.OrderDetailRepository;
import ITProject.example.WebSelling.repository.OrderRepository;
import ITProject.example.WebSelling.repository.UserRepository;
import ITProject.example.WebSelling.service.intefaces.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderService implements IOrderService {

    OrderRepository orderRepository;

    OrderMapper orderMapper;

    OrderDetailRepository orderDetailRepository;

    UserRepository userRepository;


    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);

        User user = userRepository.findByUsername(orderRequest.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_USERID)
        );

        order.setUser(user);

        //Receive Date
        //Dựa trên shipping method để quyết định
        switch (order.getShippingMethod()) {
            case "Express" -> order.setReceiveDate(LocalDate.now().plusDays(3));

            case "Standard" -> order.setReceiveDate(LocalDate.now().plusDays(4));

            case "Economy" -> order.setReceiveDate(LocalDate.now().plusDays(5));

            case "Super Express" -> order.setReceiveDate(LocalDate.now().plusDays(1));
        }


        //Shipping status
        order.setShippingStatus("In Delivery");

        //payment status
        order.setPaymentStatus("Not Yet");

        //order detail
        //List order detail mà khách hàng đặt giao
        List<OrderDetail> orderDetails = orderRequest
                .getOrderDetailIds()
                .stream()
                .map(orderDetailId -> orderDetailRepository.findById(orderDetailId).orElseThrow(
                        () -> new AppException(ErrorCode.INVALID_ORDER_ID)
                )).toList();

//        Order orderFinal = orderRepository.save(order);


        order.setOrderDetails(orderDetails);

        //total price

        float totalPrice = 0;

        for (OrderDetail orderDetail : orderDetails) {
            totalPrice += orderDetail.getTotalPrice();
        }

        order.setTotalPrice(totalPrice);

        Order orderFinal = orderRepository.save(order);

        orderDetails.forEach(orderDetail -> {
            orderDetail.setOrder(orderFinal);
            orderDetailRepository.save(orderDetail);

        });

        return orderFinal;


    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders = orderRepository.findByUser(userRepository.findByUsername(username).orElseThrow());

        System.out.println("Hello");

        return orders;
    }
}
