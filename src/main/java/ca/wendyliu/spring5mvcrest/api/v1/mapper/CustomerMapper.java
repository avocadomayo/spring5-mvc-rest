package ca.wendyliu.spring5mvcrest.api.v1.mapper;

import ca.wendyliu.spring5mvcrest.api.v1.model.CustomerDTO;
import ca.wendyliu.spring5mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    // These @Mapping annotations are ONLY necessary if property names are DIFFERENT.
    // These are here to show how to use them only.
    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName")
    })
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName")
    })
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
