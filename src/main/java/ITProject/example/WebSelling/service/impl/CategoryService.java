package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.ProductRequets.CategoryRequest;
import ITProject.example.WebSelling.entity.Category;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.CategoryMapper;
import ITProject.example.WebSelling.repository.CategoryRepository;
import ITProject.example.WebSelling.service.intefaces.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryService implements ICategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    @Override
    public Category addCategory(CategoryRequest categoryRequest) {
        return (categoryRepository.save(categoryMapper.toCategory(categoryRequest)));
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_CATEGORY_ID));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(CategoryRequest categoryRequest, Long Id) {
        Category category = categoryRepository
                .findById(Id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_CATEGORY_ID));

        categoryMapper.updateCategoryFromDTO(categoryRequest, category);

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
