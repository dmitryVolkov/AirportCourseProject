package views;

import model.Flight;
import model.Ticket;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TicketEJBBeanLocal {
    public List<Ticket> getListOfTickets(int userId, Flight flight);
    public void deleteTicket(Ticket ticket);
    public Ticket findTicketById(int id);
}
