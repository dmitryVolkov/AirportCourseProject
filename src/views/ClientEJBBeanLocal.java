package views;

import model.Flight;
import model.Ticket;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClientEJBBeanLocal {
    public List<Ticket> getListOfTicketsByFlight(Flight flight);
}
