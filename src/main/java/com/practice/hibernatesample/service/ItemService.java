package com.practice.hibernatesample.service;

import com.practice.hibernatesample.model.ItemRecord;

import java.util.List;

public interface ItemService {
    ItemRecord createItem(ItemRecord item);
    List<ItemRecord> getItemsByCategory(int categoryId);
}
