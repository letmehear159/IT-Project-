package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.ProductRequets.ManufacturerRequest;
import ITProject.example.WebSelling.entity.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ManufacturerMapper {
    Manufacturer toManufacturer(ManufacturerRequest manufacturer);

    void updateManufacturerFromDto(ManufacturerRequest dto, @MappingTarget Manufacturer manufacturer);
}
