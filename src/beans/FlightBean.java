package beans;

import model.Flight;
import views.FlightEJBBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    private int price;

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

    public String goToAddingFlight(){
        timeOfDeparture = null;
        timeOfArrival = null;
        selectedPilotId = 0;
        selectedPlaneId = 0;
        selectedRouteId = 0;
        price = 0;
        return "addFlight";
    }

    public String confirmDeletingFlight(){
        flightEJB.deleteFlight(selectedFlight);
        return "showFlights";
    }

    public String goToEditFlight(Flight flight){
        selectedFlight = flight;
        timeOfDeparture = selectedFlight.getTimeOfDeparture();
        timeOfArrival = selectedFlight.getTimeOfArrival();
        price = selectedFlight.getPrice();
        selectedPilotId = selectedFlight.getPilot().getId();
        selectedPlaneId = selectedFlight.getPlane().getId();
        selectedRouteId = selectedFlight.getRoute().getId();
        return "editFlight";
    }

    public String editFlight(){
        selectedFlight.setTimeOfDeparture(timeOfDeparture);
        selectedFlight.setTimeOfArrival(timeOfArrival);
        selectedFlight.setPrice(price);
        flightEJB.editFlight(selectedFlight, selectedPilotId, selectedPlaneId, selectedRouteId);
        return "showFlights";
    }

    public String addFlight(){
        Flight flight = new Flight();
        flight.setTimeOfDeparture(timeOfDeparture);
        flight.setTimeOfArrival(timeOfArrival);
        flight.setPrice(price);
        flightEJB.addFlight(flight, selectedPilotId, selectedPlaneId, selectedRouteId);
        return "showFlights";
    }

    public String convertJavaUtilDate(Date date){
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
