package views;

import model.Flight;

import javax.ejb.Local;
import java.util.List;

@Local
public interface FlightEJBBeanLocal {
    public List<Flight> getListOfFlights();
    public void addFlight(Flight flight, int pilotId, int planeId, int routeId);
    public void deleteFlight(Flight flight);
    public void editFlight(Flight flight, int pilotId, int planeId, int routeId);
}
