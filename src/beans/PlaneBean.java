package beans;

import views.PlaneEJBBeanLocal;
import model.Plane;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PlaneBean implements Serializable {

    @EJB
    private PlaneEJBBeanLocal planeEJBBean;

    private List<Plane> listOfPlanes;

    private Plane selectedPlane;

    @Size(min = 2, max = 30, message = "Модель должна быть от 2 до 30 символов")
    private String model;

    private int year;

    private int countOfRows;

    private int placesInRow;

    public List<Plane> getListOfPlanes() {
        listOfPlanes = planeEJBBean.getListOfPlanes();
        return listOfPlanes;
    }

    public String addPlane(){
        Plane plane = new Plane(model, year, countOfRows, placesInRow);
        planeEJBBean.addPlane(plane);
        return "showPlanes";
    }

    public String goToShowPlane(Plane plane){
        selectedPlane = plane;
        return "showInfoAboutPlane";
    }

    public String goToDeletingPlane(Plane plane){
        selectedPlane = plane;
        return "confirmDeletingPlane";
    }

    public String confirmDeletePlane(){
        planeEJBBean.deletePlane(selectedPlane);
        return "showPlanes";
    }

    public Plane getSelectedPlane() {
        return selectedPlane;
    }

    public void setSelectedPlane(Plane selectedPlane) {
        this.selectedPlane = selectedPlane;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCountOfRows() {
        return countOfRows;
    }

    public void setCountOfRows(int countOfRows) {
        this.countOfRows = countOfRows;
    }

    public int getPlacesInRow() {
        return placesInRow;
    }

    public void setPlacesInRow(int placesInRow) {
        this.placesInRow = placesInRow;
    }
}
