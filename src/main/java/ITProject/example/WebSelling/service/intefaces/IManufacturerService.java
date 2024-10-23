package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.ManufacturerRequest;
import ITProject.example.WebSelling.entity.Manufacturer;

import java.util.List;

public interface IManufacturerService {
    Manufacturer addManufacturer(ManufacturerRequest manufacturerRequest);

    Manufacturer getManufacturerById(Long id);

    List<Manufacturer> getAllManufacturers();

    Manufacturer updateManufacturer(ManufacturerRequest manufacturerRequest, Long Id);

    void deleteManufacturer(Long id);

}
