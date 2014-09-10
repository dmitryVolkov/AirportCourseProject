package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
@LocalBean
public class AbstractAirportFacade<T>{

    public AbstractAirportFacade(){}

    public void create(T entity, EntityManager em) {
        em.persist(entity);
    }

    public void edit(T entity, EntityManager em) {
        em.merge(entity);
    }

    public void remove(T entity, EntityManager em) {
        em.remove(em.merge(entity));
    }

    public T find(Class<T> entityClass, int id, EntityManager em) {
        return em.find(entityClass, id);
    }

    public List<T> findAll(EntityManager em, Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
}
