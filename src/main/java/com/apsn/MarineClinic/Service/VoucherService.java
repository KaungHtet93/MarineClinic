package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.Model.PackageEntity;
import com.apsn.MarineClinic.Model.Seaman;
import com.apsn.MarineClinic.Model.Voucher;
import com.apsn.MarineClinic.Repository.MedicalStaffRepository;
import com.apsn.MarineClinic.Repository.PackageRepository;
import com.apsn.MarineClinic.Repository.SeamanRepository;
import com.apsn.MarineClinic.Repository.VoucherRepository;
import com.apsn.MarineClinic.dto.Input.VoucherInput;
import com.apsn.MarineClinic.dto.response.VoucherResponse;
import com.apsn.MarineClinic.mapper.VoucherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private SeamanRepository seamanRepository;
    @Autowired
    private PackageRepository packageRepository;
    private VoucherMapper mapper;
    public VoucherService(VoucherMapper mapper){
        this.mapper=mapper;
    }
    //get all
    public List<VoucherResponse> getAll() {
        return mapper.toVoucherResponseList(voucherRepository.findAll()) ;
    }
    //create voucher
    public VoucherResponse create(VoucherInput input){
        PackageEntity packageEntity = packageRepository.findById(input.package_Id()).orElseThrow(RuntimeException::new);
        Seaman seaman = seamanRepository.findById(input.seaman_Id()).orElseThrow(RuntimeException::new);
        Voucher voucher=new Voucher();
        voucher.setAdditionalFee(input.additional_Fee());
        voucher.setDateTime(LocalDateTime.now());
        voucher.setSeaman(seaman);
        voucher.setAPackage(packageEntity);
        voucher.setTotalAmount(packageRepository.findPriceById(input.package_Id())+ input.additional_Fee());
        voucher.setCashier_Name(input.cashier_Name());
        voucherRepository.save(voucher);
        return mapper.toVoucherResponse(voucher);
    }
    //update voucher
    public VoucherResponse update(Long id,VoucherInput input){
        Optional<Voucher> optional= voucherRepository.findById(id);
        if(optional.isPresent()){
            PackageEntity packageEntity = packageRepository.findById(input.package_Id()).orElseThrow(RuntimeException::new);
            Seaman seaman = seamanRepository.findById(input.seaman_Id()).orElseThrow(RuntimeException::new);
            Voucher voucher=optional.get();
            voucher.setAdditionalFee(input.additional_Fee());
            voucher.setDateTime(LocalDateTime.now());
            voucher.setSeaman(seaman);
            voucher.setAPackage(packageEntity);
            voucher.setTotalAmount(packageRepository.findPriceById(input.package_Id())+ input.additional_Fee());
            voucher.setCashier_Name(input.cashier_Name());
            voucherRepository.save(voucher);
            return mapper.toVoucherResponse(voucher);
        }else throw new RuntimeException("Voucher not found");
    }
    //delete
    public boolean deleteById(Long id) {
        voucherRepository.deleteById(id);
        return true;
    }
    public List<VoucherResponse> getVoucherBetween(LocalDate startDate,LocalDate endDate){
        return mapper.toVoucherResponseList(voucherRepository.getVoucherBetween(startDate, endDate));
    }
    public VoucherResponse getById(Long id){
        return mapper.toVoucherResponse(voucherRepository.findById(id).orElseThrow(RuntimeException::new));
    }
    public List<VoucherResponse> getBySeamanName(String name){
        return mapper.toVoucherResponseList(voucherRepository.getBySeamanName(name));
    }
    public List<VoucherResponse> getByStaffName(String name){
        return mapper.toVoucherResponseList(voucherRepository.getByStaffName(name));
    }
}
