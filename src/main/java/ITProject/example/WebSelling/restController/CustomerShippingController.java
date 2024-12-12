package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.ShippingRequest.CustomerShippingRequest;
import ITProject.example.WebSelling.entity.CustomerShipping;
import ITProject.example.WebSelling.service.intefaces.ICustomerShippingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerShippings")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerShippingController {

    ICustomerShippingService customerShippingService;

    @PostMapping("/")
    public ResponseEntity<CustomerShipping> create(@RequestBody CustomerShippingRequest customerShippingRequest) {
        return ResponseEntity.ok(customerShippingService.saveCustomerShipping(customerShippingRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerShipping>> getAll() {
        return ResponseEntity.ok(customerShippingService.getAllCustomerShipping());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerShippingService.deleteCustomerShipping(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerShipping> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerShippingService.getCustomerShipping(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerShipping> update(@PathVariable Long id, @RequestBody CustomerShippingRequest request) {
        return ResponseEntity.ok(customerShippingService.updateCustomerShipping(request, id));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CustomerShipping>> getShippingAddressList(@PathVariable String username) {
        return  ResponseEntity.ok(customerShippingService.getCustomerShippingByUsername(username));
    }
}
