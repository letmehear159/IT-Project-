package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.ShippingMethodRequest;
import ITProject.example.WebSelling.entity.ShippingMethod;

import java.util.List;

public interface IShippingMethodService {
    ShippingMethod findShippingMethodById(Long id);

    ShippingMethod createShippingMethod(ShippingMethodRequest shippingMethod);

    ShippingMethod updateShippingMethod(ShippingMethodRequest shippingMethod, Long id);

    void deleteShippingMethod(Long id);

    List<ShippingMethod> findAllShippingMethods();
}
