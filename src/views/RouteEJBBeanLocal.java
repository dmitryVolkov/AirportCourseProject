package views;

import model.Route;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RouteEJBBeanLocal {
    public List<Route> getListOfRoutes();
    public void addRoute(String title, int pointOfDepartureId, int pointOfArrivalId, int distance);
    public void deleteRoute(Route route);
    public void editRoute(String title, int pointOfDepartureId, int pointOfArrivalId, int distance, Route route);
}
