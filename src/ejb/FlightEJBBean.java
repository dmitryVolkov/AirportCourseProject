package ejb;

import model.*;
import views.FlightEJBBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FlightEJBBean implements FlightEJBBeanLocal {

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    @Override
    public List<Flight> getListOfFlights(){
        return abstractAirportFacade.findAll(emA, Flight.class);
    }

    @Override
    public void addFlight(Flight flight, int pilotId, int planeId, int routeId){
        Pilot pilot = (Pilot) abstractAirportFacade.find(Pilot.class, pilotId, emA);
        Plane plane = (Plane) abstractAirportFacade.find(Plane.class, planeId, emA);
        Route route = (Route) abstractAirportFacade.find(Route.class, routeId, emA);
        flight.setPilot(pilot);
        flight.setPlane(plane);
        flight.setRoute(route);
        abstractAirportFacade.create(flight, emA);
    }

    @Override
    public void deleteFlight(Flight flight){
        abstractAirportFacade.remove(flight, emA);
    }

    @Override
    public void editFlight(Flight flight, int pilotId, int planeId, int routeId){
        Pilot pilot = (Pilot) abstractAirportFacade.find(Pilot.class, pilotId, emA);
        Plane plane = (Plane) abstractAirportFacade.find(Plane.class, planeId, emA);
        Route route = (Route) abstractAirportFacade.find(Route.class, routeId, emA);
        flight.setPilot(pilot);
        flight.setPlane(plane);
        flight.setRoute(route);
        abstractAirportFacade.edit(flight, emA);
    }
}
