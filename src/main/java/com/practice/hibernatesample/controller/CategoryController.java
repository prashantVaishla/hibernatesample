package com.practice.hibernatesample.controller;

import com.practice.hibernatesample.model.CategoryRecord;
import com.practice.hibernatesample.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("/restaurantpos/v1")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryRecord> createCategory(@RequestBody CategoryRecord categoryRecord) {
        logger.info("Creating single category: {}", categoryRecord);
        return ResponseEntity.ok(categoryService.createCategory(categoryRecord));
    }

    @PostMapping("/categories")
    public String createCategory(@RequestBody List<CategoryRecord> categoryRecords) {
        logger.info("Creating multiple categories: size={}", categoryRecords.size());
        categoryService.createCategories(categoryRecords);
        return "done";
    }

    @GetMapping("/categories/{vendorId}")
    @CrossOrigin(origins = "http://localhost:3000/")
    public ResponseEntity<List<CategoryRecord>> getCategories(@PathVariable Long vendorId) {
        logger.info("Fetching categories for vendorId={}", vendorId);

        if (vendorId == null) {
            logger.warn("Vendor ID is missing in the request");
            return ResponseEntity.badRequest().build();
        }

        List<CategoryRecord> allCategories = categoryService.getAllCategories(vendorId);

        if (allCategories != null && !allCategories.isEmpty()) {
            logger.info("Found {} categories for vendorId={}", allCategories.size(), vendorId);
            return ResponseEntity.ok(allCategories);
        } else {
            logger.warn("No categories found for vendorId={}", vendorId);
            return ResponseEntity.notFound().build();
        }
    }


}



