package beans;

import model.Flight;
import model.Ticket;
import views.TicketEJBBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TicketBean implements Serializable {

    @Inject
    private UserIdHolder userIdHolder;

    private Flight selectedFlight;

    private Ticket selectedTicket;

    private List<Ticket> listOfTickets;

    @EJB
    private TicketEJBBeanLocal ticketEJBBean;

    public List<Ticket> getListOfTickets() {
        listOfTickets = ticketEJBBean.getListOfTickets(userIdHolder.getUserId(), selectedFlight);
        return listOfTickets;
    }

    public String goToShowReservingPlaces(Flight flight){
        selectedFlight = flight;
        return "showReservingTickets";
    }

    public String goToShowInfoAboutTicket(Ticket ticket){
        selectedTicket = ticketEJBBean.findTicketById(ticket.getId());
        return "infoAboutTicket";
    }

    public String deleteTicket(Ticket ticket){
        ticketEJBBean.deleteTicket(ticket);
        return "showReservingTickets";
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public Ticket getSelectedTicket() {
        return selectedTicket;
    }
}
