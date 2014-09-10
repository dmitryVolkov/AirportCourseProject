package beans;

import ejb.PointEJBBeanLocal;
import model.Point;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PointBean implements Serializable {

    @EJB
    private PointEJBBeanLocal pointEJBBean;

    private List<Point> listOfPoints;

    private Point selectedPoint;

    @Size(min = 2, max = 30, message = "Страна должна быть от 2 до 30 символов")
    private String country;

    @Size(min = 2, max = 30, message = "Город должен быть от 2 до 30 символов")
    private String city;

    public List<Point> getListOfPoints() {
        listOfPoints = pointEJBBean.getListOfPoints();
        return listOfPoints;
    }

    public String addPoint(){
        Point point = new Point(country, city);
        pointEJBBean.addPoint(point);
        return "showPoints";
    }

    public String goToDeletingPoint(Point point){
        selectedPoint = point;
        return "confirmDeletingPoint";
    }

    public String goToEditingPoint(Point point){
        selectedPoint = point;
        country = selectedPoint.getCountry();
        city = selectedPoint.getCity();
        return "editPoint";
    }

    public String editPoint(){
        selectedPoint.setCountry(country);
        selectedPoint.setCity(city);
        pointEJBBean.editPoint(selectedPoint);
        return "showPoints";
    }

    public String confirmDeletePoint(){
        pointEJBBean.deletePoint(selectedPoint);
        return "showPoints";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Point getSelectedPoint() {
        return selectedPoint;
    }

    public void setSelectedPoint(Point selectedPoint) {
        this.selectedPoint = selectedPoint;
    }
}
