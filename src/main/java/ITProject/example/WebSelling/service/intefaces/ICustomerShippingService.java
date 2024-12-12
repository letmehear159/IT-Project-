package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.ShippingRequest.CustomerShippingRequest;
import ITProject.example.WebSelling.entity.CustomerShipping;

import java.util.List;

public interface ICustomerShippingService {
    CustomerShipping getCustomerShipping(Long userId);

    CustomerShipping saveCustomerShipping(CustomerShippingRequest customerShippingRequest);

    void deleteCustomerShipping(Long customerShippingId);

    List<CustomerShipping> getAllCustomerShipping();

    CustomerShipping updateCustomerShipping(CustomerShippingRequest customerShippingRequest, Long userId);


    List<CustomerShipping> getCustomerShippingByUsername(String username);
}
