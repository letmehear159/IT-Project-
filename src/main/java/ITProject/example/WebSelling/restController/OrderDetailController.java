package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.OrderDetailRequest;
import ITProject.example.WebSelling.dto.response.OrderDetailResponse;
import ITProject.example.WebSelling.entity.OrderDetail;
import ITProject.example.WebSelling.service.impl.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @GetMapping("/")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetails() {
        return ResponseEntity.ok(orderDetailService.getAllOrderDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailService.getOrderDetail(id));
    }

    @PostMapping("/")
    public ResponseEntity<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        return ResponseEntity.ok(orderDetailService.saveOrderDetail(orderDetailRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(
            @PathVariable Long id, @RequestBody OrderDetailRequest orderDetailRequest) {
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(orderDetailRequest, id));
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
    }

}
