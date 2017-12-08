package ca.wendyliu.springframework.api.v1.mapper;

import ca.wendyliu.spring5mvcrest.api.v1.mapper.VendorMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.VendorDTO;
import ca.wendyliu.spring5mvcrest.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "Yorozu-ya";
    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void testVendorToVendorDTO() {
        Vendor vendor = new Vendor(NAME);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void testVendorDTOtoVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}
