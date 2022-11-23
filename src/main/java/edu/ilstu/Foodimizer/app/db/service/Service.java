package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class Service<T> {
    EntityManager em = ServicesEntityManager.getInstance().getEntityManager();

    public Optional<T> get(long id, Class<T> t) {

        return Optional.ofNullable(em.find(t, id));
    }

    public List<T> getAll(Class<T> t) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(t);

        // SELECT * FROM T
        Root<T> rootEntity = cq.from(t);
        CriteriaQuery<T> all = cq.select(rootEntity);

        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void save(T t) {
        executeInsideTransaction(em -> em.persist(t));
    }

    public void update(T t, String params) {
        executeInsideTransaction(em -> em.merge(t));
    }

    public void delete(T t) {
        executeInsideTransaction(em -> em.remove(t));
    }


    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    /**
     * @param s the name of the thing to search by
     * @return
     */
    public List<T> searchLikeString(String s, Class<T> t) {
        // TODO SQL INJECTION ATTACK MITIGATION
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(t);

        // SELECT recipe FROM Recipe WHERE LOWER(recipe) LIKE LOWER('%s%')
        Root<T> rootEntity = cq.from(t);
        CriteriaQuery<T> byNameQuery = cq.select(rootEntity).where(cb.like(cb.lower(rootEntity.get("name")), cb.lower(cb.literal("%" + s + "%"))));

        TypedQuery<T> byName = em.createQuery(byNameQuery);
        return byName.getResultList();
    }
}
