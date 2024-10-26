package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.VoucherRequest;
import ITProject.example.WebSelling.entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VoucherMapper {

    Voucher toVoucher(VoucherRequest voucherRequest);

    void updateVoucher(VoucherRequest voucherRequest, @MappingTarget Voucher voucher);
}
