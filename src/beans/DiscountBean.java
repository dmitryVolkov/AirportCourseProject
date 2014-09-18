package beans;

import model.Discount;
import model.Flight;
import model.Route;
import views.CommonEJBBeanLocal;
import views.FlightEJBBeanLocal;
import views.RouteEJBBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class DiscountBean implements Serializable {

    @EJB
    private RouteEJBBeanLocal routeEJBBeanLocal;

    private List<Route> listOfRoutes;

    @EJB
    private CommonEJBBeanLocal commonEJBBean;

    private int percentOfDiscount;

    @Size(min = 2, max = 40, message = "Название скидки может быть от 2 до 40 символов")
    private String title;

    private int route_id;

    @EJB
    private RouteEJBBeanLocal routeEJBBean;

    private List<Discount> listOfDiscounts;

    public List<Discount> getListOfDiscounts() {
        listOfDiscounts = commonEJBBean.getListOfDiscounts();
        return listOfDiscounts;
    }

    public List<Route> getListOfRoutes() {
        listOfRoutes = routeEJBBeanLocal.getListOfRoutes();
        List<Route> newListOfRoutes = new ArrayList<Route>();
        for (Route route : listOfRoutes){
            boolean flagNoHavingDiscount = true;
            for (Discount discount : listOfDiscounts){
                if (route.getId() == discount.getRoute_id())
                    flagNoHavingDiscount = false;
            }
            if (flagNoHavingDiscount){
                newListOfRoutes.add(route);
            }
        }
        listOfRoutes = newListOfRoutes;
        return listOfRoutes;
    }


    public String addDiscount(){
        Discount discount = new Discount(percentOfDiscount, title, route_id);
        commonEJBBean.addDiscount(discount);
        percentOfDiscount = 0;
        title = "";
        route_id = 0;
        return "showDiscounts";
    }

    public Route findRouteByDiscountId(int id){
        return commonEJBBean.findRouteByDiscountId(id);
    }

    public String deleteDiscount(Discount discount){
        commonEJBBean.deleteDiscount(discount);
        return "showDiscounts";
    }

    public Discount findDiscountByRouteId(int route_id){
        return commonEJBBean.getDiscountByRouteId(route_id);
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getPercentOfDiscount() {
        return percentOfDiscount;
    }

    public void setPercentOfDiscount(int percentOfDiscount) {
        this.percentOfDiscount = percentOfDiscount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
