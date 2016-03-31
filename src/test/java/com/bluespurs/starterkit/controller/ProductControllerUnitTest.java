package com.bluespurs.starterkit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluespurs.starterkit.UnitTest;

public class ProductControllerUnitTest extends UnitTest{
	private MockMvc mockMvc;

    @Before
    public void setUp() {
        super.setUp();
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();
    }

    /**
     * Test the get Functionality.
     *
     * @see ProductController#getLowestPrice()
     */
    @Test
    public void testGetLowestPrice() throws Exception {
        mockMvc.perform(get("/product/search?name=ipad"))
            .andExpect(status().isOk());
        
    }

}
