package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.VoucherRequest;
import ITProject.example.WebSelling.entity.Voucher;

import java.util.List;

public interface IVoucherService {
    Voucher createVoucher(VoucherRequest voucherRequest);

    Voucher updateVoucher(VoucherRequest voucherRequest, Long voucherId);

    void deleteVoucher(Long voucherId);

    List<Voucher> getAllVouchers();

    Voucher getVoucherById(Long voucherId);


}
