package views;

import model.Point;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PointEJBBeanLocal {
    public List<Point> getListOfPoints();
    public void addPoint(Point point);
    public void editPoint(Point point);
    public void deletePoint(Point point);
}
