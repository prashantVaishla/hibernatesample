package com.practice.hibernatesample.dao;

import com.practice.hibernatesample.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional//(isolation = Isolation.DEFAULT)
    public Category createCategory(Category category){
        Optional.ofNullable(category.getId())
                .ifPresentOrElse(id->entityManager.merge(category),
                        ()->entityManager.persist(category));
        return category;
    }

    public Category findCategoryById(Long id){
       return entityManager.find(Category.class,id);
    }

    public List<Category> findCategoriesByVendorId(Long vendorId){
        String sql="SELECT category_id, title, name, vendor_id FROM category WHERE vendor_id = :vendorId";
        Query query = entityManager.createNativeQuery(sql, Category.class);
        query.setParameter("vendorId",vendorId);
        return query.getResultList();
    }

    @Transactional
    public void deleteCategory(Long id){
        Category categoryById = findCategoryById(id);
        Optional.ofNullable(categoryById)
                .ifPresent(category->entityManager.detach(category));
    }

    @Transactional
    public void savaCatagories(List<Category> categories){
        categories.stream().filter(c->c.getId()==null)
                .forEach(e->entityManager.persist(e));
    }
}
