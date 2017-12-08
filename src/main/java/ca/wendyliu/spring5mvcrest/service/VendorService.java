package ca.wendyliu.spring5mvcrest.service;

import ca.wendyliu.spring5mvcrest.api.v1.model.VendorDTO;
import ca.wendyliu.spring5mvcrest.api.v1.model.VendorListDTO;

import java.util.List;

public interface VendorService {

    // GET
    VendorListDTO getAllVendors();

    // GET
    VendorDTO getVendorById(Long id);

    // POST
    VendorDTO createVendor(VendorDTO vendorDTO);

    // PUT
    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    // PATCH
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    // DELETE
    void deleteVendorById(Long id);
}
