package ca.wendyliu.spring5mvcrest.api.v1.mapper;

import ca.wendyliu.spring5mvcrest.api.v1.model.VendorDTO;
import ca.wendyliu.spring5mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    // Mapstruct will auto-map Vendor.name to VendorDTO.name since they share the same property name
    VendorDTO vendorToVendorDTO(Vendor from);
    Vendor vendorDTOtoVendor(VendorDTO from);
}
