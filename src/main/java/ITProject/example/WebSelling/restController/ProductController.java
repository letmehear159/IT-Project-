package ITProject.example.WebSelling.restController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

    @PostMapping("/")
    public ResponseEntity<?> createProduct(@RequestBody Object product) {
        return ResponseEntity.ok().build();
    }



}
