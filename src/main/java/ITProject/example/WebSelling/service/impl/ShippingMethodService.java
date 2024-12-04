package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.ShippingRequest.ShippingMethodRequest;
import ITProject.example.WebSelling.entity.ShippingMethod;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.ShippingMethodMapper;
import ITProject.example.WebSelling.repository.ShippingMethodRepository;
import ITProject.example.WebSelling.service.intefaces.IShippingMethodService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ShippingMethodService implements IShippingMethodService {

    ShippingMethodRepository shippingMethodRepository;

    ShippingMethodMapper shippingMethodMapper;

    @Override
    public ShippingMethod findShippingMethodById(Long id) {
        return shippingMethodRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_ID)
        );
    }

    @Override
    public ShippingMethod createShippingMethod(ShippingMethodRequest shippingMethodRequest) {
        var shippingMethod = shippingMethodMapper.toShippingMethod(shippingMethodRequest);

        return shippingMethodRepository.save(shippingMethod);
    }


    @Override
    public ShippingMethod updateShippingMethod(ShippingMethodRequest shippingMethodRequest, Long id) {
        var shippingMethod = findShippingMethodById(id);

        shippingMethodMapper.updateShippingMethod(shippingMethodRequest, shippingMethod);

        return shippingMethodRepository.save(shippingMethod);

    }

    @Override
    public void deleteShippingMethod(Long id) {

        shippingMethodRepository.deleteById(id);

    }

    @Override
    public List<ShippingMethod> findAllShippingMethods() {
        return shippingMethodRepository.findAll();
    }

}
