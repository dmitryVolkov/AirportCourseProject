package ejb;

import model.Flight;
import model.Pilot;
import model.Ticket;
import views.TicketEJBBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TicketEJBBean implements TicketEJBBeanLocal {

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    @Override
    public Ticket findTicketById(int id){
        return emA.find(Ticket.class, id);
    }

    @Override
    public List<Ticket> getListOfTickets(int userId, Flight flight){
        List<Ticket> listOfTickets =  abstractAirportFacade.findAll(emA, Ticket.class);
        List<Ticket> resultList = new ArrayList<Ticket>();
        for (Ticket ticket : listOfTickets){
            if ((ticket.getUserId() == userId) && (ticket.getStatus().equals("Б")) &&
                    (ticket.getFlight().getId() == flight.getId()))
                resultList.add(ticket);
        }
        return resultList;
    }

    @Override
    public void deleteTicket(Ticket ticket){
        abstractAirportFacade.remove(ticket, emA);
    }
}
