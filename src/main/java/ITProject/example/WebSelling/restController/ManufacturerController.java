package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.ManufacturerRequest;
import ITProject.example.WebSelling.entity.Manufacturer;
import ITProject.example.WebSelling.service.impl.ManufacturerService;
import ITProject.example.WebSelling.service.intefaces.IManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturers")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ManufacturerController {

    IManufacturerService manufacturerService;

    @PostMapping("/")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody ManufacturerRequest manufacturerRequest) {
        return ResponseEntity.ok().body(manufacturerService.addManufacturer(manufacturerRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return ResponseEntity.ok().body(manufacturerService.getAllManufacturers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManufacturerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(manufacturerService.getManufacturerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Long id,
                                                           @RequestBody ManufacturerRequest manufacturerRequest) {
        return ResponseEntity.ok(manufacturerService.updateManufacturer(manufacturerRequest, id));

    }

    @DeleteMapping("/{id}")
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }
}
