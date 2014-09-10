package ejb;

import model.Point;
import model.Route;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RouteEJBBean implements RouteEJBBeanLocal {

    public RouteEJBBean(){}

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    @Override
    public List<Route> getListOfRoutes(){
        return abstractAirportFacade.findAll(emA, Route.class);
    }

    @Override
    public void addRoute(String title, int pointOfDepartureId, int pointOfArrivalId, int distance){
        Point pointOfDep = (Point) abstractAirportFacade.find(Point.class, pointOfDepartureId, emA);
        Point pointOfArr = (Point) abstractAirportFacade.find(Point.class, pointOfArrivalId, emA);
        Route route = new Route(title, pointOfDep, pointOfArr, distance);
        abstractAirportFacade.create(route, emA);
    }

    @Override
    public void deleteRoute(Route route){
        abstractAirportFacade.remove(route, emA);
    }

    @Override
    public void editRoute(String title, int pointOfDepartureId, int pointOfArrivalId, int distance, Route route){
        route = emA.find(Route.class, route.getId());
        Point pointOfDep = (Point) abstractAirportFacade.find(Point.class, pointOfDepartureId, emA);
        Point pointOfArr = (Point) abstractAirportFacade.find(Point.class, pointOfArrivalId, emA);
        route.setPoint1(pointOfDep);
        route.setPoint2(pointOfArr);
        route.setTitle(title);
        route.setDistance(distance);
        abstractAirportFacade.edit(route, emA);
    }

}
