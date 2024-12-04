package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.ShippingRequest.CustomerShippingRequest;
import ITProject.example.WebSelling.entity.CustomerShipping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerShippingMapper {

    CustomerShipping toCustomerShipping(CustomerShippingRequest customerShipping);

    void updateCustomerShipping(CustomerShippingRequest customerShippingRequest
            , @MappingTarget CustomerShipping customerShipping);


}
