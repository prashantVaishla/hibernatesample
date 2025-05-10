package com.practice.hibernatesample.service;

import com.practice.hibernatesample.model.CategoryRecord;
import com.practice.hibernatesample.model.ItemRecord;

import java.util.List;

public interface CategoryService {
    CategoryRecord createCategory (CategoryRecord categoryRecord);

    void createCategories (List<CategoryRecord> categoryRecords);

    List<CategoryRecord> getAllCategories(Long vendorId);

}
