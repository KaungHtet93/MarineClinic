package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.Voucher;
import com.apsn.MarineClinic.dto.response.SeamanNameResponse;
import com.apsn.MarineClinic.dto.response.VoucherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoucherMapper {

    // Map the seaman object to seamanNameResponse
    @Mapping(source = "seaman", target = "seamanNameResponse")
    VoucherResponse toVoucherResponse(Voucher voucher);

    List<VoucherResponse> toVoucherResponseList(List<Voucher> vouchers);

    // IMPORTANT: Manual mapping method for Seaman -> SeamanNameResponse
    default SeamanNameResponse map(com.apsn.MarineClinic.Model.Seaman seaman) {
        if (seaman == null) {
            return null;
        }
        return new SeamanNameResponse(seaman.getName());
    }
}
