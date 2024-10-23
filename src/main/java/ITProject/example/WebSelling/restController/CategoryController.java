package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.ManufacturerRequest;
import ITProject.example.WebSelling.entity.Manufacturer;
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

    ManufacturerService manufacturerService;

    @PostMapping("/")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody ManufacturerRequest manufacturerRequest) {
        return ResponseEntity.ok().body(manufacturerService.addManufacturer(manufacturerRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return ResponseEntity.ok().body(manufacturerService.getAllManufacturers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManufacturerById(@PathVariable int id) {
        return ResponseEntity.ok().body(new Manufacturer());
    }

    @PutMapping("/manufacturer/{name}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable String name,
                                                           @RequestBody ManufacturerRequest manufacturerRequest) {
        return ResponseEntity.ok(manufacturerService.updateManufacturer(manufacturerRequest, name));

    }

    @DeleteMapping("/{id}")
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }
}
