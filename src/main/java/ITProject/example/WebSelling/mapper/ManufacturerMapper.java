package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.ManufacturerRequest;
import ITProject.example.WebSelling.dto.request.UserRequest;
import ITProject.example.WebSelling.entity.Manufacturer;
import ITProject.example.WebSelling.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ManufacturerMapper {
    Manufacturer toManufacturer(ManufacturerRequest manufacturer);

    void updateManufacturerFromDto(ManufacturerRequest dto, @MappingTarget Manufacturer manufacturer);
}
