package com.practice.hibernatesample.serviceimpl;

import com.practice.hibernatesample.dao.CategoryRepository;
import com.practice.hibernatesample.dao.ItemRepository;
import com.practice.hibernatesample.entities.Category;
import com.practice.hibernatesample.entities.Item;
import com.practice.hibernatesample.model.ItemRecord;
import com.practice.hibernatesample.service.ItemService;
import com.practice.hibernatesample.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

   private static final Logger logger= LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemDao;

    @Autowired
    private CategoryRepository categoryDao;
    

    @Override
    public ItemRecord createItem(ItemRecord item) {
        logger.info("Creating item: {}",item);
        Item itemEntity = CommonUtils.itemRecordToEntity(item);

        Item savedItem = itemDao.saveItem(itemEntity);
        ItemRecord result= CommonUtils.itemEntityToModel(savedItem);
        logger.debug("Item created with id {}: ",result.id());
        return result;
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public List<ItemRecord> getItemsByCategory(int categoryId) {
        logger.info("Fetching item for category id: {}",categoryId);
        Category categoryById = categoryDao.findCategoryById((long) categoryId);
        List<Item> itemByCategory = itemDao.findItemByCategory(categoryById);

        List<ItemRecord> itemRecords = itemByCategory.stream().map(CommonUtils::itemEntityToModel).toList();

        logger.debug("Found {} items for category {} ",itemRecords.size(),categoryId);
        return  itemRecords;
    }


}
