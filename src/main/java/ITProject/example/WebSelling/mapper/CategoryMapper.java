package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.ProductRequets.CategoryRequest;
import ITProject.example.WebSelling.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);

    void updateCategoryFromDTO(CategoryRequest dto, @MappingTarget Category category);
}
