package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.OrderDetailRequest;
import ITProject.example.WebSelling.dto.request.OrderDetailVoucherRequest;
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
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        orderDetail.setTotalPrice(orderDetail.getProduct().getPrice());


//        }

        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));

    }


    @Override
    public OrderDetailResponse updateOrderDetail(OrderDetailVoucherRequest orderDetailRequest) {
//        if (orderDetailRequest.getQuantity() <= 0) {
//            //Để ý đoạn này, đoạn này chưa hoàn thiện
//            deleteOrderDetail(orderDetailId);
//        }
        var orderDetailId = orderDetailRequest.getOrderId();

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_ORDER_DETAIL_ID));

        //Update số lượng
        //Voucher nên để xử lý ở javascript. Sau khi nhập voucher xác nhận order hàng thì mới bắt đầu gửi lại
        //request update orderDetail.

        orderDetail.setQuantity(orderDetailRequest.getQuantity());

        //Voucher and total price
        //Cứ cho là ở lúc nhập voucher ở FE là đã kiểm tra voucher hợp lệ, còn dùng được.
        Voucher voucher = voucherRepository.findByVoucherName(orderDetailRequest.getVoucherName());

        //Áp mã voucher chỉ áp cho sản phẩm đầu tiên, từ 2 trở đi thì như cũ


        var orderDetailPrice = orderDetail.getProduct().getPrice();

        var percentage = voucher.getPercentage();

        var discountPrice = orderDetailPrice * percentage;


        orderDetail.setTotalPrice(orderDetailPrice * (orderDetail.getQuantity() - 1) //Tổng giá từ 2th sản phẩm trở đi
                + orderDetailPrice - discountPrice > voucher.getMaximumMoney()
                ? voucher.getMaximumMoney() : discountPrice
        );

        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));

        //Vấn đề là khi khách hàng nhập voucher thì sẽ hiển thị ra giá giảm
        //Nhưng nó sẽ không cập nhật vào luôn db mà chỉ khi khách hàng đặt thì mới cập nhật vào db
        //Giải pháp: Gửi 1 request của tất cả order detail đang đặt hàng để cập nhật sau đó mới đặt hàng

    }


    @Override
    public void deleteOrderDetail(Long orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);

    }

    @Override
    public List<OrderDetailResponse> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream().map(orderDetailMapper::toOrderDetailResponse).toList();
    }

    @Override
    public List<OrderDetailResponse> updateOrderDetails(List<OrderDetailVoucherRequest> orderDetailRequests) {
        List<OrderDetailResponse> responses = new ArrayList<>();

        orderDetailRequests.forEach(orderDetailRequest -> {
            responses.add(updateOrderDetail(orderDetailRequest));
        });

        return responses;
    }


}
