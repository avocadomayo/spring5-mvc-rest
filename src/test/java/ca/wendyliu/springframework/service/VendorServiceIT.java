package ca.wendyliu.springframework.service;

import ca.wendyliu.spring5mvcrest.Spring5MvcRestApplication;
import ca.wendyliu.spring5mvcrest.api.v1.mapper.VendorMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.VendorDTO;
import ca.wendyliu.spring5mvcrest.bootstrap.Bootstrap;
import ca.wendyliu.spring5mvcrest.domain.Vendor;
import ca.wendyliu.spring5mvcrest.repository.CategoryRepository;
import ca.wendyliu.spring5mvcrest.repository.CustomerRepository;
import ca.wendyliu.spring5mvcrest.repository.VendorRepository;
import ca.wendyliu.spring5mvcrest.service.VendorService;
import ca.wendyliu.spring5mvcrest.service.VendorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Spring5MvcRestApplication.class)
@DataJpaTest
public class VendorServiceIT {

    private static final Long ID = 1L;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Vendor data");
        System.out.println(vendorRepository.findAll().size());

        // Setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void patchVendorUpdateName() throws Exception {
        String updatedName = "updatedName";

        Vendor originalVendor = vendorRepository.getOne(ID);
        assertNotNull(originalVendor);

        // Save original name
        String originalName = originalVendor.getName();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(updatedName);

        vendorService.patchVendor(ID, vendorDTO);

        Vendor updatedVendor = vendorRepository.findVendorById(ID).get();

        assertNotNull(updatedVendor);
        assertEquals(updatedName, updatedVendor.getName());
        assertNotEquals(originalName, updatedVendor.getName());
    }
}
