package ca.wendyliu.springframework.service;

import ca.wendyliu.spring5mvcrest.api.v1.mapper.VendorMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.VendorDTO;
import ca.wendyliu.spring5mvcrest.api.v1.model.VendorListDTO;
import ca.wendyliu.spring5mvcrest.controller.v1.VendorController;
import ca.wendyliu.spring5mvcrest.domain.Vendor;
import ca.wendyliu.spring5mvcrest.repository.VendorRepository;
import ca.wendyliu.spring5mvcrest.service.VendorService;
import ca.wendyliu.spring5mvcrest.service.VendorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class VendorServiceTest {

    private static final String NAME = "Yorozu-ya";
    private static final Long ID = 1L;

    private VendorService vendorService;

    @Mock
    private VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void testGetAllVendors() throws Exception {
        // Given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);

        // When
        VendorListDTO vendorDTOs = vendorService.getAllVendors();

        // Then
        assertEquals(3, vendorDTOs.getVendors().size());
        assertThat(vendorDTOs.getVendors().size(), is(equalTo(3)));
    }

    @Test
    public void testGetVendorById() throws Exception {
        // Given
        Vendor vendor = new Vendor(NAME);


        // Mockito BDD syntax
        given(vendorRepository.findVendorById(any())).willReturn(Optional.of(vendor));

        // When
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        // Then
        then(vendorRepository).should(times(1)).findVendorById(anyLong());


        // Then
        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void testCreateVendor() throws Exception {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        // When
        VendorDTO savedDTO = vendorService.createVendor(vendorDTO);

        // Then
        assertEquals(vendorDTO.getName(), savedDTO.getName());
    }

    @Test
    public void testSaveVendorByDTO() throws Exception {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor(vendorDTO.getName());
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        // When
        VendorDTO savedDTO = vendorService.saveVendorByDTO(ID, vendorDTO);

        // Then
        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorURL());
    }

    @Test
    public void testDeleteVendorById() throws Exception {
        vendorRepository.delete(ID);

        // Verify this runs one time
        verify(vendorRepository, times(1)).delete(anyLong());
    }
}
