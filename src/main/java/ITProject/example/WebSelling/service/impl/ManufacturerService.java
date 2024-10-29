package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.ManufacturerRequest;
import ITProject.example.WebSelling.entity.Manufacturer;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.ManufacturerMapper;
import ITProject.example.WebSelling.repository.ManufacturerRepository;
import ITProject.example.WebSelling.service.intefaces.IManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ManufacturerService implements IManufacturerService {

    ManufacturerMapper manufacturerMapper;
    ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer addManufacturer(ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturer = manufacturerMapper.toManufacturer(manufacturerRequest);

        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer getManufacturerById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_MANUFACTURER_ID));
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer updateManufacturer(ManufacturerRequest manufacturerRequest, Long Id) {
        Manufacturer manufacturer = manufacturerRepository
                .findById(Id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_MANUFACTURER_ID));

        manufacturerMapper.updateManufacturerFromDto(manufacturerRequest, manufacturer);

        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public void deleteManufacturer(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
