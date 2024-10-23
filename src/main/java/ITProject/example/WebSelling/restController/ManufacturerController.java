package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.entity.Manufacturer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manufacturers")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ManufacturerController {
    @PostMapping("/")
    public ResponseEntity<?> addManufacturer(@RequestBody Object newManufacturer) {
        return ResponseEntity.ok().body(newManufacturer);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllManufacturers() {
        return ResponseEntity.ok().body(new Manufacturer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManufacturerById(@PathVariable int id) {
        return ResponseEntity.ok().body(new Manufacturer());
    }


}
