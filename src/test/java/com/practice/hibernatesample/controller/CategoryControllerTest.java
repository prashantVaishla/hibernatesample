package com.practice.hibernatesample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.hibernatesample.model.CategoryRecord;
import com.practice.hibernatesample.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//for unit test,
//@SpringBootTest - to load application context, in integration testing

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void testGetCategories() throws Exception{
        CategoryRecord categoryRecord=new CategoryRecord(1L,"starters","soup",1L);
        List<CategoryRecord> categoryRecords = List.of(categoryRecord);

        Mockito.when(categoryService.getAllCategories(1L)).thenReturn(categoryRecords);

        mockMvc.perform(MockMvcRequestBuilders.get("/restaurantpos/v1/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$.length()").value(1));

    }

    @Test
    public void testGetCategories_NotFound() throws Exception{
       Mockito.when(categoryService.getAllCategories(1L)).thenReturn(null);

       mockMvc.perform((MockMvcRequestBuilders.get("/restaurantpos/v1/categories/1")))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateCategory() throws Exception{
        CategoryRecord categoryRecord=new CategoryRecord(1L,"starters","soup",1L);
        Mockito.when(categoryService.createCategory(Mockito.any(CategoryRecord.class))).thenReturn(categoryRecord);
        mockMvc.perform(MockMvcRequestBuilders.post("/restaurantpos/v1/category")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(categoryRecord)))
                .andExpect(status().isOk());


    }

}
