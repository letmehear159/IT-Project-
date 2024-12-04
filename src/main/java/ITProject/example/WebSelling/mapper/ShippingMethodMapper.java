package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.ShippingRequest.ShippingMethodRequest;
import ITProject.example.WebSelling.entity.ShippingMethod;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ShippingMethodMapper {
    ShippingMethod toShippingMethod(ShippingMethodRequest shippingMethodRequest);

    void updateShippingMethod(ShippingMethodRequest shippingMethodRequest,@MappingTarget ShippingMethod shippingMethod);
}
