package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.CategoryRequest;
import ITProject.example.WebSelling.dto.request.ManufacturerRequest;
import ITProject.example.WebSelling.entity.Category;
import ITProject.example.WebSelling.entity.Manufacturer;
import ITProject.example.WebSelling.service.impl.CategoryService;
import ITProject.example.WebSelling.service.impl.ManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryController {

    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok().body(categoryService.addCategory(categoryRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryByID(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                   @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryRequest,id ));

    }


    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
