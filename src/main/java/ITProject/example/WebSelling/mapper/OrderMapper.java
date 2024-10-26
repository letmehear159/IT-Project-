package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.OrderRequest;
import ITProject.example.WebSelling.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    Order toOrder(OrderRequest orderRequest);


}
