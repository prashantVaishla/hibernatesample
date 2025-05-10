package com.practice.hibernatesample.controller;

import com.practice.hibernatesample.model.ItemRecord;
import com.practice.hibernatesample.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantpos/v1")
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @PostMapping("/item")
    public ItemRecord createItem(@RequestBody ItemRecord item){
        logger.info("Creating single item: {}",item);
        return itemService.createItem(item);

    }

    @GetMapping("/item/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000/")
    public List<ItemRecord> getItems(@PathVariable int categoryId){
        logger.info("Fetching items for the category id {}",categoryId);
        return itemService.getItemsByCategory(categoryId);
    }
}
