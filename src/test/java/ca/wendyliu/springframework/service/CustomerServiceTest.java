package ca.wendyliu.springframework.service;

import ca.wendyliu.spring5mvcrest.api.v1.mapper.CustomerMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.CustomerDTO;
import ca.wendyliu.spring5mvcrest.controller.v1.CustomerController;
import ca.wendyliu.spring5mvcrest.domain.Customer;
import ca.wendyliu.spring5mvcrest.repository.CustomerRepository;
import ca.wendyliu.spring5mvcrest.service.CustomerService;
import ca.wendyliu.spring5mvcrest.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class CustomerServiceTest {

    public static final String FIRST_NAME = "Wendy";
    public static final String LAST_NAME = "Liu";
    public static final Long ID = 1L;

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        // Given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        // When
        List<CustomerDTO> customerDTOs = customerService.getAllCustomers();

        // Then
        assertEquals(3, customerDTOs.size());
    }

    @Test
    public void getCustomerById() throws Exception {
        // Given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));

        // WHen
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        // Then
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
    }

    @Test
    public void createNewCustomerTest() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // When
        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        // Then
        assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(CustomerController.BASE_URL + "/1", savedDTO.getCustomerURL());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // When
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(ID, customerDTO);

        // Then
        assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(CustomerController.BASE_URL + "/1", savedDTO.getCustomerURL());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        customerRepository.delete(ID);

        // Verify this runs one time
        verify(customerRepository, times(1)).delete(anyLong());
    }
}
