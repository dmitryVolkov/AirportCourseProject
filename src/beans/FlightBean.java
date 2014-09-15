package beans;

import model.Flight;
import views.FlightEJBBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Named
@SessionScoped
public class FlightBean implements Serializable {

    @EJB
    private FlightEJBBeanLocal flightEJB;

    private List<Flight> listOfFlights;

    private Flight selectedFlight;

    private Date timeOfDeparture;

    private Date timeOfArrival;

    private int selectedPilotId;

    private int selectedPlaneId;

    private int selectedRouteId;

    public List<Flight> getListOfFlights() {
        listOfFlights = flightEJB.getListOfFlights();
        return listOfFlights;
    }

    public String goToShowFlight(Flight flight){
        selectedFlight = flight;
        return "showInfoAboutFlight";
    }

    public String goToDeletingFlight(Flight flight){
        selectedFlight = flight;
        return "confirmDeletingFlight";
    }

    public String confirmDeletingFlight(){
        flightEJB.deleteFlight(selectedFlight);
        return "showFlights";
    }

    public String goToEditFlight(Flight flight){
        selectedFlight = flight;
        timeOfDeparture = selectedFlight.getTimeOfDeparture();
        timeOfArrival = selectedFlight.getTimeOfArrival();
        selectedPilotId = selectedFlight.getPilot().getId();
        selectedPlaneId = selectedFlight.getPlane().getId();
        selectedRouteId = selectedFlight.getRoute().getId();
        return "editFlight";
    }

    public String editFlight(){
        selectedFlight.setTimeOfDeparture(timeOfDeparture);
        selectedFlight.setTimeOfArrival(timeOfArrival);
        flightEJB.editFlight(selectedFlight, selectedPilotId, selectedPlaneId, selectedRouteId);
        return "showFlights";
    }

    public String addFlight(){
        Flight flight = new Flight();
        flight.setTimeOfDeparture(timeOfDeparture);
        flight.setTimeOfArrival(timeOfArrival);
        flightEJB.addFlight(flight, selectedPilotId, selectedPlaneId, selectedRouteId);
        return "showFlights";
    }

    public String convertJavaUtilDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public Date getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(Date timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public Date getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(Date timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public int getSelectedPilotId() {
        return selectedPilotId;
    }

    public void setSelectedPilotId(int selectedPilotId) {
        this.selectedPilotId = selectedPilotId;
    }

    public int getSelectedPlaneId() {
        return selectedPlaneId;
    }

    public void setSelectedPlaneId(int selectedPlaneId) {
        this.selectedPlaneId = selectedPlaneId;
    }

    public int getSelectedRouteId() {
        return selectedRouteId;
    }

    public void setSelectedRouteId(int selectedRouteId) {
        this.selectedRouteId = selectedRouteId;
    }
}
