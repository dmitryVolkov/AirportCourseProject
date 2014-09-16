package ejb;

import model.Flight;
import model.Group;
import model.Ticket;
import views.ClientEJBBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ClientEJBBean implements ClientEJBBeanLocal {

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    @Override
    public List<Ticket> getListOfTicketsByFlight(Flight flight){
        CriteriaQuery<Ticket> criteriaQuery = emA.getCriteriaBuilder().createQuery(Ticket.class);
        Root ticketRoot = criteriaQuery.from(Ticket.class);
        Predicate predicate1 = ticketRoot.get("flight").in(flight);
        criteriaQuery.select(ticketRoot).where(predicate1);
        List<Ticket> tickets = emA.createQuery(criteriaQuery).getResultList();
        return tickets;
    }
}
