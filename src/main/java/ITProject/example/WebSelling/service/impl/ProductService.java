package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.ProductImageRequest;
import ITProject.example.WebSelling.dto.request.ProductRequest;
import ITProject.example.WebSelling.dto.response.ProductResponse;
import ITProject.example.WebSelling.entity.Product;
import ITProject.example.WebSelling.entity.ProductImage;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.ProductMapper;
import ITProject.example.WebSelling.repository.CategoryRepository;
import ITProject.example.WebSelling.repository.ManufacturerRepository;
import ITProject.example.WebSelling.repository.ProductImageRepository;
import ITProject.example.WebSelling.repository.ProductRepository;
import ITProject.example.WebSelling.service.intefaces.IProductService;
import ITProject.example.WebSelling.utils.MessageKeys;
import ITProject.example.WebSelling.components.LocalizationUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService implements IProductService {
    ProductMapper productMapper;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ManufacturerRepository manufacturerRepository;
    ProductImageRepository productImageRepository;
    LocalizationUtils localizationUtils;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest, MultipartFile thumbnail) throws IOException {

        Product product = productMapper.toProduct(productRequest);

        product.setCategory(categoryRepository
                .findByCategoryName(productRequest.getCategoryName())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NAME)));

        product.setManufacturer(manufacturerRepository
                .findByManufacturerName(productRequest.getManufacturerName())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NAME)));

        product.setStatus(1);


        //        productResponse.setCategoryName(product.getCategory().getCategoryName());
//
//        productResponse.setManufacturerName(product.getManufacturer().getManufacturerName());

        if (isImageFile(thumbnail)) {
            // Lưu file và cập nhật thumbnail trong DTO
            String filename = storeFile(thumbnail); // Thay thế hàm này với code của bạn để lưu file
            product.setThumbnail(filename);
        }
        product = productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));

        //Chỉ có thể thêm hoặc cập nhật specifications, không thể xóa toàn bộ để thêm mới được
        productMapper.updateProductFromDto(productRequest, product);

        var specifications = productRequest.getSpecifications();
        for (var entry : specifications.entrySet()) {
            product.getSpecifications().put(entry.getKey(), entry.getValue());
        }

        product.setCategory(categoryRepository
                .findByCategoryName(productRequest.getCategoryName())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NAME)));

        product.setManufacturer(manufacturerRepository
                .findByManufacturerName(productRequest.getManufacturerName())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NAME)));

        return productMapper.toProductResponse(productRepository.save(product));

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        var products = productRepository.findAll();

        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public ProductResponse getProduct(Long id) {

        return productMapper.toProductResponse(productRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID)));
    }

    public List<ProductImage> uploadImages(Long productId, List<MultipartFile> files) throws IOException {

        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));

        files = files == null ? new ArrayList<MultipartFile>() : files;

        if (files.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new AppException(ErrorCode.EXCEED_MAXIMUM_ALLOW_NUMBER);
        }

        List<ProductImage> productImages = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.getSize() == 0) {
                continue;
            }

            // Kiểm tra kích thước file và định dạng
            if (file.getSize() > 10 * 1024 * 1024) { // Kích thước > 10MB
                throw new AppException(ErrorCode.FILE_IMAGE_TOO_LARGE);
            }

            String contentType = file.getContentType();

            if (contentType == null || !contentType.startsWith("image/")) {
                throw new AppException(ErrorCode.UPLOAD_IMAGE_MUST_BE_IMAGE_TYPE);
            }

            // Lưu file và cập nhật thumbnail trong DTO
            String filename = storeFile(file); // Thay thế hàm này với code của bạn để lưu file

            //lưu vào đối tượng product trong DB
            ProductImage productImage = createProductImage(
                    existingProduct.getProductId(),
                    ProductImageRequest.builder()
                            .imageUrl(filename)
                            .build()
            );

            productImages.add(productImage);
        }
        return productImages;
    }

    public ProductImage createProductImage(Long productId, ProductImageRequest productImageDTO) {
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new AppException(
                                ErrorCode.INVALID_ID));

        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();

        //Ko cho insert quá 5 ảnh cho 1 sản phẩm
        int size = productImageRepository.findByProductProductId(productId).size();

        if (size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new AppException(ErrorCode.EXCEED_MAXIMUM_ALLOW_NUMBER);
        }

        return productImageRepository.save(newProductImage);
    }


    public String storeFile(MultipartFile file) throws IOException {

        if (!isImageFile(file) || file.getOriginalFilename() == null) {
            throw new IOException("Invalid image format");
        }

        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // Thêm UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename = UUID.randomUUID() + "_" + filename;

        // Đường dẫn đến thư mục mà bạn muốn lưu file
        java.nio.file.Path uploadDir = Paths.get("src/main/resources/static/uploads");

        // Kiểm tra và tạo thư mục nếu nó không tồn tại
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Đường dẫn đầy đủ đến file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);

        // Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFilename;
    }

    private boolean isImageFile(MultipartFile file) {

        String contentType = file.getContentType();

        return contentType != null && contentType.startsWith("image/");
    }
}

