package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.ProductRequets.CategoryRequest;
import ITProject.example.WebSelling.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(CategoryRequest categoryRequest);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(CategoryRequest categoryRequest, Long Id);

    void deleteCategory(Long id);

}
