package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.CustomerShippingRequest;
import ITProject.example.WebSelling.entity.CustomerShipping;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.CustomerShippingMapper;
import ITProject.example.WebSelling.repository.CustomerShippingRepository;
import ITProject.example.WebSelling.repository.UserRepository;
import ITProject.example.WebSelling.service.intefaces.ICustomerShippingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerShippingService implements ICustomerShippingService {
    CustomerShippingRepository customerShippingRepository;
    CustomerShippingMapper customerShippingMapper;
    UserRepository userRepository;

    @Override
    public CustomerShipping getCustomerShipping(Long userId) {
        return customerShippingRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_CUSTOMER_SHIPPING_ID)
        );
    }

    @Override
    public CustomerShipping saveCustomerShipping(CustomerShippingRequest customerShippingRequest) {
        var customerShipping = customerShippingMapper.toCustomerShipping(customerShippingRequest);

        var user = userRepository.findById(customerShippingRequest.getUserId()).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_CUSTOMER_SHIPPING_ID)
        );

        List<CustomerShipping> shippingList = user.getCustomerShipping()
                == null ? new ArrayList<>()
                : user.getCustomerShipping();

        shippingList.add(customerShipping);

        user.setCustomerShipping(shippingList);

        userRepository.save(user);

        return customerShipping;
    }

    @Override
    public void deleteCustomerShipping(Long customerShippingId) {
        customerShippingRepository.deleteById(customerShippingId);

    }

    @Override
    public List<CustomerShipping> getAllCustomerShipping() {
        return customerShippingRepository.findAll();
    }

    @Override
    public CustomerShipping updateCustomerShipping(CustomerShippingRequest customerShippingRequest, Long userId) {
        CustomerShipping customerShipping = customerShippingRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_CUSTOMER_SHIPPING_ID)
        );

        customerShippingMapper.updateCustomerShipping(customerShippingRequest, customerShipping);

        return customerShippingRepository.save(customerShipping);

    }
}
