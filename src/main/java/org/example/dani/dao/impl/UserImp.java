package org.example.dani.dao.impl;

import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.User;
import org.example.dani.util.UtilEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class UserImp implements IDao<User> {

    EntityManager em = UtilEntity.getEntityManager();

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        try {
            User user = em.find(User.class, id);

            if (user == null) {
                throw new Exception("The indicated user does not exist");
            }

            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();

            System.out.println("--> ERROR: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        try {
            em.getTransaction().begin();

            if (user.getId() != null && user.getId() > 0) {
                User userUpdate = getById(user.getId());
                userUpdate.setName(user.getName());
                userUpdate.setLastname(user.getLastname());
                userUpdate.setEmail(user.getEmail());
                userUpdate.setPassword(user.getPassword());
                userUpdate.setUserType(user.getUserType());
                userUpdate.setEnabled(user.getEnabled());

                em.persist(userUpdate);
            } else {
                em.persist(user);
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

            User user = getById(id);
            em.remove(user);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}
