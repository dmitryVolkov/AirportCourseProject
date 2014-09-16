package beans;

import com.sun.javafx.font.FontConfigManager;
import model.Flight;
import model.Ticket;
import views.ClientEJBBeanLocal;
import views.FlightEJBBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ClientBean implements Serializable {

    @Inject
    private UserIdHolder userIdHolder;

    private int userId;

    @EJB
    private ClientEJBBeanLocal clientEJBBean;

    private Flight selectedFlight;

    private List<Ticket> listOfTickets;

    private int selectedRow;

    private int selectedPlace;

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public int checkPlaceOnBusyOrReserve(int row, int place){
        for (Ticket ticket : listOfTickets){
            if ((ticket.getRow() == row) && (ticket.getPlace() == place) && (ticket.getStatus().equals("З")))
                return 2;
            else if ((ticket.getRow() == row) && (ticket.getPlace() == place) && (ticket.getStatus().equals("Б")))
                return 1;
        }
        return 0;
    }

    public String buyTicket(){
        return "resultBuyingTicket";
    }

    public String goToShowInfoAboutFlight(Flight flight){
        selectedFlight = flight;
        return "showInfoAboutFlight";
    }

    public String goToShowPlaces(Flight flight){
        selectedFlight = flight;
        listOfTickets = clientEJBBean.getListOfTicketsByFlight(selectedFlight);
        return "showPlaces";
    }

    public List<Ticket> getListOfTickets() {
        return listOfTickets;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public int getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(int selectedPlace) {
        this.selectedPlace = selectedPlace;
    }

    public void validateOnCountsForUser(ComponentSystemEvent event){
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = event.getComponent();
        UIInput uiInput = (UIInput) component.findComponent("row");
        String inputRowId = uiInput.getClientId();
        int counter = 0;
        for (Ticket ticket : listOfTickets){
            if ((ticket.getFlight().getId() == selectedFlight.getId()) &&
                    (ticket.getUserId() == userIdHolder.getUserId()) && (ticket.getStatus().equals("Б")))
                counter++;
        }
        if (counter >= 4){
            FacesMessage msg = new FacesMessage("Нельзя забронировать более 4х мест на один полет");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(inputRowId, msg);
            fc.renderResponse();
        }
    }

    public void userIdListener(ActionEvent actionEvent){
        int userId = ((Integer) actionEvent.getComponent().getAttributes().get("userid")).intValue();
        userIdHolder.setUserId(userId);
    }

    public void validateOnBusyOrReserve(ComponentSystemEvent event){
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputRow = (UIInput) components.findComponent("row");
        String inputRowId = uiInputRow.getClientId();
        String row = uiInputRow.getLocalValue() == null ? ""
                : uiInputRow.getLocalValue().toString();
        UIInput uiInputPlace = (UIInput) components.findComponent("place");
        String inputPlaceId = uiInputPlace.getClientId();
        String place = uiInputPlace.getLocalValue() == null ? ""
                : uiInputPlace.getLocalValue().toString();
        int result = checkPlaceOnBusyOrReserve(Integer.parseInt(row),Integer.parseInt(place));
        if (result == 1 || result == 2){
            FacesMessage msg = new FacesMessage("Ряд " + row + " Место " + place + " уже забронировано");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(inputRowId, msg);
            fc.renderResponse();
        }
    }
}
