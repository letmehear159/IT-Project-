package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.OrderDetailRequest;
import ITProject.example.WebSelling.dto.response.OrderDetailResponse;
import ITProject.example.WebSelling.entity.OrderDetail;
import ITProject.example.WebSelling.entity.Voucher;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.OrderDetailMapper;
import ITProject.example.WebSelling.repository.OrderDetailRepository;
import ITProject.example.WebSelling.repository.ProductRepository;
import ITProject.example.WebSelling.repository.ShoppingCartRepository;
import ITProject.example.WebSelling.repository.VoucherRepository;
import ITProject.example.WebSelling.service.intefaces.IOrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderDetailService implements IOrderDetailService {
    OrderDetailRepository orderDetailRepository;

    OrderDetailMapper orderDetailMapper;

    ProductRepository productRepository;

    ShoppingCartRepository shoppingCartRepository;

    VoucherRepository voucherRepository;


    @Override
    public OrderDetailResponse getOrderDetail(Long orderDetailId) {
        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository
                .findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID)));
    }

    @Override
    public OrderDetailResponse saveOrderDetail(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailRequest);

        //Lần đầu tiên chưa có số lượng, chỉ thêm vào giỏ hàng nên mặc định là 1
        orderDetail.setQuantity(1);

        orderDetail.setProduct(productRepository.findById(orderDetailRequest.getProductId()).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_PRODUCT_ID)));

        orderDetail.setShoppingCart(shoppingCartRepository.findById(orderDetailRequest.getCartId()).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_SHOPPINGCART_ID)));

        //1 voucher chỉ áp dụng cho 1 sản phẩm, nếu sản phẩm đó từ 2 trở đi thì giá như cũ.
//        if (orderDetailRequest.getVoucherId().describeConstable().isPresent()) {
//
//            Voucher voucher = voucherRepository.findById(orderDetailRequest.getVoucherId()).get();
//
//            float percentage = voucher.getPercentage();
//
//            orderDetail.setTotalPrice(orderDetail.getPrice() * (100 - percentage) / 100);
//
//        } else {

        orderDetail.setTotalPrice(orderDetailRequest.getPrice());

//        }

        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));

    }

    @Override
    public OrderDetailResponse updateOrderDetail(OrderDetailRequest orderDetailRequest, Long orderDetailId) {
        if (orderDetailRequest.getQuantity() <= 0) {
            //Để ý đoạn này, đoạn này chưa hoàn thiện
            deleteOrderDetail(orderDetailId);
        }

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_ID));

        //Update số lượng
        //Voucher nên để xử lý ở javascript. Sau khi nhập voucher xác nhận order hàng thì mới bắt đầu gửi lại
        //request update orderDetail.
        orderDetailMapper.updateOrderDetailFromDTO(orderDetailRequest, orderDetail);

        orderDetail.setTotalPrice(orderDetail.getPrice() * orderDetail.getQuantity());

        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));

    }




    @Override
    public void deleteOrderDetail(Long orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);

    }

    @Override
    public List<OrderDetailResponse> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream().map(orderDetailMapper::toOrderDetailResponse).toList();
    }

}
