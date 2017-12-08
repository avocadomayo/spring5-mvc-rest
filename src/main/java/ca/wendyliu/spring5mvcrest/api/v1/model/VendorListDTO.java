package ca.wendyliu.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
// Wrapper object to match the API at https://api.predic8.de/shop/docs
public class VendorListDTO {

    List<VendorDTO> vendors;
}
