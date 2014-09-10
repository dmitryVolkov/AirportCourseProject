package ejb;

import model.Plane;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
public interface PlaneEJBBeanLocal {
    public List<Plane> getListOfPlanes();
    public void addPlane(Plane plane);
    public void deletePlane(Plane plane);
}
