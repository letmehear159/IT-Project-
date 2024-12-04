package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.OrderProductRequest.OrderDetailRequest;
import ITProject.example.WebSelling.dto.response.OrderDetailResponse;
import ITProject.example.WebSelling.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {
    OrderDetail toOrderDetail(OrderDetailRequest orderDetailRequest);

    void updateOrderDetailFromDTO(OrderDetailRequest dto, @MappingTarget OrderDetail orderDetail);

    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

}
