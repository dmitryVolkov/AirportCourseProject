package beans;

import ejb.PointEJBBeanLocal;
import ejb.RouteEJBBeanLocal;
import model.Point;
import model.Route;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RouteBean implements Serializable {

    @EJB
    private RouteEJBBeanLocal routeEJBBeanLocal;

    @Inject
    private PointBean pointBean;

    private List<Route> listOfRoutes;

    private List<Point> listOfPoints;

    private Route selectedRoute;

    @Size(min = 2, max = 30, message = "Название маршрута должно быть от 2 до 30 символов")
    private String title;

    private int pointOfDepartureId;

    private int pointOfArrivalId;

    private int distance;

    public String addRoute(){
        routeEJBBeanLocal.addRoute(title, pointOfDepartureId, pointOfArrivalId, distance);
        return "showRoutes";
    }

    public String goToEditRoute(Route route){
        selectedRoute = route;
        title = route.getTitle();
        pointOfDepartureId = route.getPoint1().getId();
        pointOfArrivalId = route.getPoint2().getId();
        distance = route.getDistance();
        return "editRoute";
    }

    public String editRoute(){
        routeEJBBeanLocal.editRoute(title, pointOfDepartureId, pointOfArrivalId, distance, selectedRoute);
        return "showRoutes";
    }

    public List<Route> getListOfRoutes() {
        listOfRoutes = routeEJBBeanLocal.getListOfRoutes();
        return listOfRoutes;
    }

    public List<Point> getListOfPoints() {
        listOfPoints = pointBean.getListOfPoints();
        return listOfPoints;
    }

    public String goToConfirmDeleteRoute(Route route){
        selectedRoute = route;
        return "confirmDeletingRoute";
    }

    public String confirmDeleteRoute(){
        routeEJBBeanLocal.deleteRoute(selectedRoute);
        return "showRoutes";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPointOfDepartureId() {
        return pointOfDepartureId;
    }

    public void setPointOfDepartureId(int pointOfDepartureId) {
        this.pointOfDepartureId = pointOfDepartureId;
    }

    public int getPointOfArrivalId() {
        return pointOfArrivalId;
    }

    public void setPointOfArrivalId(int pointOfArrivalId) {
        this.pointOfArrivalId = pointOfArrivalId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public PointBean getPointBean() {
        return pointBean;
    }

    public Route getSelectedRoute() {
        return selectedRoute;
    }
}
