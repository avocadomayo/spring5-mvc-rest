package ca.wendyliu.springframework.api.v1.mapper;

import ca.wendyliu.spring5mvcrest.api.v1.mapper.CustomerMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.CustomerDTO;
import ca.wendyliu.spring5mvcrest.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String FIRST_NAME = "Wendy";
    public static final String LAST_NAME = "Liu";
    public static final String URL = "api/wendyliu";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        // Given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        // When
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // Then
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}
