package com.practice.hibernatesample.service;

import com.practice.hibernatesample.dao.CategoryRepository;
import com.practice.hibernatesample.entities.Category;
import com.practice.hibernatesample.model.CategoryRecord;
import org.aspectj.util.Reflection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @MockitoBean
    private CategoryRepository categoryDao;

    @Test
    public void testCreateCategory(){
        CategoryRecord categoryRecord=new CategoryRecord(1L,"starters","soup",1L);
        Category category=new Category();
        category.setId(1L);
        category.setTitle("starters");
        category.setName("soup");
        category.setVendorId(1L);

        Mockito.when(categoryDao.createCategory(Mockito.any(Category.class))).thenReturn(category);
        CategoryRecord savedCategory = categoryService.createCategory(categoryRecord);

        Assertions.assertEquals(savedCategory.id(),
                category.getId());
    }

    @Test
    public void testConvertToCategoryEntites() throws Exception{
        CategoryRecord categoryRecord=new CategoryRecord(1L,"starters","soup",1L);
        List<CategoryRecord> categoryRecords = List.of(categoryRecord);

        Method declaredMethod = categoryService.getClass().getDeclaredMethod("convertToCategoryEntites", List.class);
        declaredMethod.setAccessible(true);
        List<Category>  result = (List<Category> )declaredMethod.invoke(categoryService,categoryRecords);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get(0).getId(),categoryRecord.id());
    }
}
