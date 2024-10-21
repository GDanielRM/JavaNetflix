package org.example.dani.dao.impl;

import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.Movie;
import org.example.dani.util.UtilEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class MovieImp implements IDao<Movie> {

    EntityManager em = UtilEntity.getEntityManager();

    @Override
    public List<Movie> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    @Override
    public Movie getById(Integer id) {
        try {
            Movie movie = em.find(Movie.class, id);

            if (movie == null) {
                throw new Exception("The indicated movie does not exist");
            }

            return movie;
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();

            System.out.println("--> ERROR: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Movie movie) {
        try {
            em.getTransaction().begin();

            if (movie.getId() != null && movie.getId() > 0) {
                Movie movieUpdate = getById(movie.getId());
                movieUpdate.setName(movie.getName());
                movieUpdate.setYear(movie.getYear());
                movieUpdate.setCategoryId(movie.getCategoryId());
                movieUpdate.setDirectorId(movie.getDirectorId());
                movieUpdate.setSynopsis(movie.getSynopsis());

                em.persist(movieUpdate);
            } else {
                em.persist(movie);
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

            Movie movie = getById(id);
            em.remove(movie);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}
