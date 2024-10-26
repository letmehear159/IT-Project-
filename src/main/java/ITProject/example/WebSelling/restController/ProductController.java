package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.ProductRequest;
import ITProject.example.WebSelling.dto.response.ProductResponse;
import ITProject.example.WebSelling.entity.ProductImage;
import ITProject.example.WebSelling.service.impl.ProductService;
import ITProject.example.WebSelling.service.intefaces.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {
    IProductService productService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> createProduct(@RequestPart ProductRequest product,
                                                         @RequestPart("thumbnail") MultipartFile thumbnail)
            throws IOException {
        return ResponseEntity.ok().body(productService.addProduct(product, thumbnail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest product) {
        return ResponseEntity.ok().body(productService.updateProduct(product, id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping(value = "/uploads/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ProductImage>> uploadProduct(@PathVariable Long id,
                                                            @RequestParam("files") List<MultipartFile> file)
            throws IOException {
        return ResponseEntity.ok(productService.uploadImages(id, file));
    }
}
