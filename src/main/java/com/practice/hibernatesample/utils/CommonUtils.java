package com.practice.hibernatesample.utils;


import com.practice.hibernatesample.entities.Category;
import com.practice.hibernatesample.entities.Item;
import com.practice.hibernatesample.model.CategoryRecord;
import com.practice.hibernatesample.model.ItemRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * Converts ItemRecord to Item entity.
     * @param itemRecord the ItemRecord to convert
     * @return the corresponding Item entity
     */
    public static Item itemRecordToEntity(ItemRecord itemRecord) {
        logger.debug("Converting ItemRecord to Item entity: {}", itemRecord);

        Item item = new Item();
        item.setTitle(itemRecord.title());
        item.setPrice(itemRecord.price());

        Category category = new Category();
        category.setId(itemRecord.category().id());
        category.setName(itemRecord.category().name());
        item.setCategory(category);
        item.setId(itemRecord.id());

        logger.debug("Converted Item entity: {}", item);
        return item;
    }

    /**
     * Converts Item entity to ItemRecord model.
     * @param item the Item entity to convert
     * @return the corresponding ItemRecord model
     */
    public static ItemRecord itemEntityToModel(Item item) {
        logger.debug("Converting Item entity to ItemRecord: {}", item);

        Category category = item.getCategory();
        CategoryRecord categoryRecord = new CategoryRecord(category.getId(), category.getTitle(), category.getName(), category.getVendorId());

        ItemRecord itemRecord = new ItemRecord(item.getId(), item.getTitle(), categoryRecord, item.getPrice());

        logger.debug("Converted ItemRecord: {}", itemRecord);
        return itemRecord;
    }

    /**
     * Converts CategoryRecord to Category entity.
     * @param categoryRecord the CategoryRecord to convert
     * @return the corresponding Category entity
     */
    public static Category CategoryRecordToEntity(CategoryRecord categoryRecord) {
        logger.debug("Converting CategoryRecord to Category entity: {}", categoryRecord);

        Category category = new Category();
        category.setName(categoryRecord.name());
        category.setTitle(categoryRecord.title());
        category.setId(categoryRecord.id());
        category.setVendorId(categoryRecord.vendorId());

        logger.debug("Converted Category entity: {}", category);
        return category;
    }

    /**
     * Converts Category entity to CategoryRecord model.
     * @param category the Category entity to convert
     * @return the corresponding CategoryRecord model
     */
    public static CategoryRecord categoryEntityToModel(Category category) {
        logger.debug("Converting Category entity to CategoryRecord: {}", category);

        CategoryRecord categoryRecord = new CategoryRecord(category.getId(), category.getTitle(), category.getName(), category.getVendorId());

        logger.debug("Converted CategoryRecord: {}", categoryRecord);
        return categoryRecord;
    }

}
