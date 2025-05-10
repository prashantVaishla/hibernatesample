package com.practice.hibernatesample.serviceimpl;

import com.practice.hibernatesample.dao.CategoryRepository;
import com.practice.hibernatesample.entities.Category;
import com.practice.hibernatesample.model.CategoryRecord;
import com.practice.hibernatesample.service.CategoryService;
import com.practice.hibernatesample.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryDao;


    /**
     * @param categoryRecord
     * @return
     */
    @Override
    public CategoryRecord createCategory(CategoryRecord categoryRecord) {
        logger.info("Creating category : {}",categoryRecord);
        Category category= categoryDao.createCategory(CommonUtils.CategoryRecordToEntity(categoryRecord));
        CategoryRecord result = CommonUtils.categoryEntityToModel(category);

        logger.debug("Created category with ID: {}", result.id());
        return result;
    }


    @Override
    public void createCategories(List<CategoryRecord> categoryRecord) {
        logger.info("Creating {} categories", categoryRecord.size());

        List<Category> categories = convertToCategoryEntites(categoryRecord);
        categoryDao.savaCatagories(categories);

        logger.debug("Categories saved: {}", categories.size());
    }

    /**
     * @param vendorId
     * @return
     */
    @Override
    public List<CategoryRecord> getAllCategories(Long vendorId) {
        logger.info("Fetching categories for vendorId={}", vendorId);

        List<Category> categoriesByVendorId = categoryDao.findCategoriesByVendorId(vendorId);
        List<CategoryRecord> result = convertToCategoryRecord(categoriesByVendorId);

        logger.debug("Found {} categories for vendorId={}", result.size(), vendorId);
        return result;
    }


    private List<Category> convertToCategoryEntites(List<CategoryRecord> categoryRecord) {
        logger.trace("Converting CategoryRecord list to Category entity list");
        return categoryRecord.stream().map(CommonUtils::CategoryRecordToEntity).toList();
    }

    private List<CategoryRecord> convertToCategoryRecord(List<Category> categories){
        logger.trace("Converting Category entity list to CategoryRecord list");
        return categories.stream().map(CommonUtils::categoryEntityToModel).toList();
    }

}
