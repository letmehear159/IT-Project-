package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.OrderProductRequest.OrderRequest;
import ITProject.example.WebSelling.entity.Order;
import ITProject.example.WebSelling.service.intefaces.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderController {

    IOrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Order>> getOrder(@PathVariable String username) {
        return ResponseEntity.ok(orderService.getOrdersByUsername(username));
    }
}
