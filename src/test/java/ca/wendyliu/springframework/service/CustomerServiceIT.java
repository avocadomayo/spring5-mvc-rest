package ca.wendyliu.springframework.service;

import ca.wendyliu.spring5mvcrest.Spring5MvcRestApplication;
import ca.wendyliu.spring5mvcrest.api.v1.mapper.CustomerMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.CustomerDTO;
import ca.wendyliu.spring5mvcrest.bootstrap.Bootstrap;
import ca.wendyliu.spring5mvcrest.domain.Customer;
import ca.wendyliu.spring5mvcrest.repository.CategoryRepository;
import ca.wendyliu.spring5mvcrest.repository.CustomerRepository;
import ca.wendyliu.spring5mvcrest.repository.VendorRepository;
import ca.wendyliu.spring5mvcrest.service.CustomerService;
import ca.wendyliu.spring5mvcrest.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;


// Integration test. Brings up a small subset of Spring Context- only the data layer (not controllers or services)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Spring5MvcRestApplication.class)
@DataJpaTest
// The @DataJpaTest annotation looks for a @SpringBootConfiguration annotation in the current package. If it cannot
// find it there, it will traverse the package hierarchy until it finds it. In this case it won't find it- since
// the Application class is located in ca.wendyliu.spring5mvcrest.
// NOTE: The configuration is attached to the Application class- hence we need to give @SpringBootTest(classes =
// Spring5MvcRestApplication.class).
public class CustomerServiceIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Customer data");
        System.out.println(customerRepository.findAll().size());

        // Setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run(); // Load data

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        // Save original name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertNotEquals(originalFirstName, updatedCustomer.getFirstName());
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        // Save original name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers found: " + customers.size());

        return customers.get(0).getId();
    }
}
