package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.ProductRequest;
import ITProject.example.WebSelling.dto.response.ProductResponse;
import ITProject.example.WebSelling.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductResponse addProduct(ProductRequest productRequest, MultipartFile thumbnail) throws IOException;

    ProductResponse updateProduct(ProductRequest productRequest, Long productId);

    void deleteProduct(Long id);


    List<ProductResponse> getAllProducts();

    ProductResponse getProduct(Long id);


}
