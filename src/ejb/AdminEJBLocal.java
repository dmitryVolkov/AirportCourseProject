package ejb;

import model.Pilot;
import model.Plane;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AdminEJBLocal {
    public List<Pilot> getListOfPilots();
    public List<Plane> getListOfPlanes();
    public void addPilot(Pilot pilot);
    public void addPlane(Plane plane);
    public void deletePilot(Pilot pilot);
    public void deletePlane(Plane plane);
}
