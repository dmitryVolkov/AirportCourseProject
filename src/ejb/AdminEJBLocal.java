package ejb;

import model.Pilot;
import model.Plane;
import model.Point;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AdminEJBLocal {
    public List<Pilot> getListOfPilots();
    public List<Plane> getListOfPlanes();
    public List<Point> getListOfPoints();
    public void addPilot(Pilot pilot);
    public void addPlane(Plane plane);
    public void addPoint(Point point);
    public void editPoint(Point point);
    public void deletePilot(Pilot pilot);
    public void deletePlane(Plane plane);
    public void deletePoint(Point point);
}
