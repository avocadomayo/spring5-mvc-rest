package ca.wendyliu.springframework.controller;

import ca.wendyliu.spring5mvcrest.Spring5MvcRestApplication;
import ca.wendyliu.spring5mvcrest.api.v1.model.VendorDTO;
import ca.wendyliu.spring5mvcrest.api.v1.model.VendorListDTO;
import ca.wendyliu.spring5mvcrest.controller.v1.VendorController;
import ca.wendyliu.spring5mvcrest.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static ca.wendyliu.springframework.controller.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Example where we use Spring to wire everything up!
// Use the following annotations to convert this into an integration test
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
@ContextConfiguration(classes = Spring5MvcRestApplication.class)
public class VendorControllerTest {

    public static final String NAME = "Yorozu-ya";
    public static final Long ID = 1L;

    // Rather than initMocks(), we ask the Spring Context to create a Bean and inject that into our class
    @MockBean
    VendorService vendorService;

    // Provided by Spring Context
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetAllVendors() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(NAME);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setName("Pachimon-ya");

        VendorListDTO vendors = new VendorListDTO(Arrays.asList(vendorDTO1, vendorDTO2));

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        // Mockito BDD syntax
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testCreateVendor() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setName(vendor.getName());
        returnVendor.setVendorURL(VendorController.BASE_URL + "/1");

        when(vendorService.createVendor(any(VendorDTO.class))).thenReturn(returnVendor);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testPatchVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendorURL(VendorController.BASE_URL + "/1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteVendorById() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(vendorService).deleteVendorById(anyLong());
    }
}
