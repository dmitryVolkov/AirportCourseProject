package ejb;

import model.Group;
import views.CommonEJBBeanLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class CommonEJBBean implements CommonEJBBeanLocal {

    @PersistenceContext(name = "persistence/airusers", unitName= "AirusersPersistenceUnit")
    private EntityManager emU;

    public CommonEJBBean(){}

    @Override
    public Group getGroupByTitle(String title){
        CriteriaQuery<Group> criteriaQuery = emU.getCriteriaBuilder().createQuery(Group.class);
        Root roleRoot = criteriaQuery.from(Group.class);
        Predicate predicate1 = roleRoot.get("groupName").in(title);
        criteriaQuery.select(roleRoot).where(predicate1);
        Group clientRole = emU.createQuery(criteriaQuery).getSingleResult();
        return clientRole;
    }
}
