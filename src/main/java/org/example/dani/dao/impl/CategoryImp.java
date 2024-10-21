package org.example.dani.dao.impl;

import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.Category;
import org.example.dani.util.UtilEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryImp implements IDao<Category> {

    EntityManager em = UtilEntity.getEntityManager();

    @Override
    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public Category getById(Integer id) {
        try {
            Category category = em.find(Category.class, id);

            if (category == null) {
                throw new Exception("The indicated category does not exist");
            }

            return category;
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();

            System.out.println("--> ERROR: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Category category) {
        try {
            em.getTransaction().begin();

            if (category.getId() != null && category.getId() > 0) {
                Category categoryUpdate = getById(category.getId());
                categoryUpdate.setName(category.getName());
                categoryUpdate.setClassification(category.getClassification());

                em.persist(categoryUpdate);
            } else {
                em.persist(category);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            em.getTransaction().begin();

            Category category = getById(id);
            em.remove(category);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}
