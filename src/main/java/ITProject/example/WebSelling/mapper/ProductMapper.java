package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.ProductRequest;
import ITProject.example.WebSelling.dto.request.UserRequest;
import ITProject.example.WebSelling.dto.response.ProductResponse;
import ITProject.example.WebSelling.entity.Product;
import ITProject.example.WebSelling.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "manufacturer", ignore = true)
    Product toProduct(ProductRequest productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductResponse toProductResponse(Product product);


    @Mapping(target = "specifications", ignore = true)
    void updateProductFromDto(ProductRequest dto, @MappingTarget Product product);


}
