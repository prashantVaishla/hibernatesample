package com.practice.hibernatesample.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordDataSetup {

    public static List<CategoryRecord> categories = List.of(
            new CategoryRecord(13L, "Starter (Veg)", "Starter1", 1L),
            new CategoryRecord(14L, "Starter (Non-Veg)", "Starter2", 1L),
            new CategoryRecord(15L, "Main Course", "mainCourse", 1L),
            new CategoryRecord(16L, "Pizza", "Pizza", 1L),
            new CategoryRecord(17L, "Dessert", "Dessert", 1L),
            new CategoryRecord(18L, "Beverage", "Beverage", 1L),
            new CategoryRecord(19L, "Soups", "Soups", 1L),
            new CategoryRecord(20L, "Rum", "Rum", 1L),
            new CategoryRecord(21L, "South Indian", "SouthIndian", 1L)
    );

    // Create a map for quick lookup by category ID
    public static Map<Long, CategoryRecord> categoryMap = categories.stream()
            .collect(Collectors.toMap(CategoryRecord::id, c -> c));

    public static List<ItemRecord> items = List.of(
            new ItemRecord(0L, "Aloo Chaat", categoryMap.get(13L), 150),
            new ItemRecord(1L, "Paneer Tikka", categoryMap.get(13L), 250),
            new ItemRecord(2L, "Corn", categoryMap.get(13L), 20),
            new ItemRecord(3L, "Crispy Corn", categoryMap.get(13L), 10),
            new ItemRecord(4L, "Chilly Paneer", categoryMap.get(13L), 450),
            new ItemRecord(5L, "Mushroom Corn", categoryMap.get(13L), 10),
            new ItemRecord(6L, "Tikki Chat", categoryMap.get(13L), 10),
            new ItemRecord(7L, "Chicken Tikka", categoryMap.get(14L), 260),
            new ItemRecord(8L, "Tandoori Chicken", categoryMap.get(14L), 260),
            new ItemRecord(9L, "Cheese Kebab", categoryMap.get(14L), 260),
            new ItemRecord(10L, "Hot & Sour", categoryMap.get(19L), 110),
            new ItemRecord(11L, "Thal Soup", categoryMap.get(19L), 55),
            new ItemRecord(12L, "Chicken clear", categoryMap.get(19L), 450),
            new ItemRecord(13L, "Manchow", categoryMap.get(19L), 150),
            new ItemRecord(14L, "Farmhouse", categoryMap.get(16L), 450),
            new ItemRecord(15L, "Vegie Delight", categoryMap.get(16L), 480),
            new ItemRecord(16L, "Lassi", categoryMap.get(18L), 40),
            new ItemRecord(17L, "Coffee", categoryMap.get(18L), 10),
            new ItemRecord(18L, "Soda", categoryMap.get(18L), 40),
            new ItemRecord(19L, "GulabJamun", categoryMap.get(17L), 10),
            new ItemRecord(20L, "Rasogulla", categoryMap.get(17L), 10),
            new ItemRecord(21L, "Paneer Masala", categoryMap.get(15L), 450),
            new ItemRecord(22L, "Old Monk", categoryMap.get(20L), 100)
    );

}
