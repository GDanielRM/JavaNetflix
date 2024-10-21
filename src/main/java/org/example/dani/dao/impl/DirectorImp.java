package org.example.dani.dao.impl;

import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.Director;
import org.example.dani.util.UtilEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class DirectorImp implements IDao<Director> {

    EntityManager em = UtilEntity.getEntityManager();

    @Override
    public List<Director> findAll() {
        return em.createQuery("SELECT d FROM Director d", Director.class).getResultList();
    }

    @Override
    public Director getById(Integer id) {
        try {
            Director director = em.find(Director.class, id);

            if (director == null) {
                throw new Exception("The indicated director does not exist");
            }

            return director;
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();

            System.out.println("--> ERROR: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Director director) {
        try {
            em.getTransaction().begin();

            if (director.getId() != null && director.getId() > 0) {
                Director directorUpdate = getById(director.getId());
                directorUpdate.setName(director.getName());
                directorUpdate.setCountryOrigin(director.getCountryOrigin());
                directorUpdate.setOscar(director.getOscar());

                em.persist(directorUpdate);
            } else {
                em.persist(director);
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

            Director director = getById(id);
            em.remove(director);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}
