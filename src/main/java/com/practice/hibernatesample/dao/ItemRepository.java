package com.practice.hibernatesample.dao;

import com.practice.hibernatesample.entities.Category;
import com.practice.hibernatesample.entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ItemRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Item saveItem(Item item){
        if(item.getId()==null){
            entityManager.persist(item);
        }else {
            entityManager.merge(item);
        }

        return item;
    }

    public Item findById(long id){
        return entityManager.find(Item.class,id);
    }

    //example using native postgre sql query
    public List<Item> findItemByCategory(Category category){
        Query query = entityManager.createNativeQuery("Select * from item where category_id=?", Item.class);
        query.setParameter(1,category.getId());
        return query.getResultList();
    }

    @Transactional
    public void deleteItemById(Long id){
        Item item = findById(id);
        if(item!=null){
            entityManager.remove(item);
        }
    }
}
