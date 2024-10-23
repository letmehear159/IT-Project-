package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.CategoryRequest;
import ITProject.example.WebSelling.dto.request.ManufacturerRequest;
import ITProject.example.WebSelling.entity.Category;
import ITProject.example.WebSelling.entity.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);

    void updateCategoryFromDTO(CategoryRequest dto, @MappingTarget Category category);
}
