package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.VoucherRequest;
import ITProject.example.WebSelling.entity.Voucher;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.mapper.VoucherMapper;
import ITProject.example.WebSelling.repository.VoucherRepository;
import ITProject.example.WebSelling.service.intefaces.IVoucherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VoucherService implements IVoucherService {

    VoucherRepository voucherRepository;
    VoucherMapper voucherMapper;


    @Override
    public Voucher createVoucher(VoucherRequest voucherRequest) {

        Voucher voucher = voucherMapper.toVoucher(voucherRequest);

        voucher.setStatus(1);

        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(VoucherRequest voucherRequest, Long voucherId) {
        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_ID)
        );

        voucherMapper.updateVoucher(voucherRequest, voucher);

        return voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucher(Long voucherId) {
        voucherRepository.deleteById(voucherId);

    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher getVoucherById(Long voucherId) {
        return voucherRepository.findById(voucherId).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_ID)
        );
    }

    @Override
    public Voucher getVoucherByVoucherName(String voucherName) {
        return voucherRepository.findByVoucherName(voucherName);
    }


}
