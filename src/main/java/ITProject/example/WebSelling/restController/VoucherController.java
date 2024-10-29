package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.VoucherRequest;
import ITProject.example.WebSelling.entity.Voucher;
import ITProject.example.WebSelling.service.intefaces.IVoucherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vouchers")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VoucherController {

    IVoucherService voucherService;

    @GetMapping("/")
    public ResponseEntity<List<Voucher>> getVoucher() {
        return ResponseEntity.ok(voucherService.getAllVouchers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(voucherService.getVoucherById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Voucher> createVoucher(@RequestBody VoucherRequest voucherRequest) {
        return ResponseEntity.ok(voucherService.createVoucher(voucherRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable Long id,
                                                 @RequestBody VoucherRequest voucherRequest) {
        return ResponseEntity.ok(voucherService.updateVoucher(voucherRequest, id));
    }

    @DeleteMapping("{id}")
    public void deleteVoucher(@PathVariable Long id) {
        voucherService.deleteVoucher(id);
    }

    @GetMapping("/name/{voucherName}")
    public ResponseEntity<Voucher> getVoucherByName(@PathVariable String voucherName) {
        return ResponseEntity.ok(voucherService.getVoucherByVoucherName(voucherName));
    }
}
