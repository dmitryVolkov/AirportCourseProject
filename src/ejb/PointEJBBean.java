package ejb;

import model.Point;
import views.PointEJBBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PointEJBBean implements PointEJBBeanLocal {

    public PointEJBBean(){}

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    @Override
    public List<Point> getListOfPoints(){
        return abstractAirportFacade.findAll(emA, Point.class);
    }

    @Override
    public void addPoint(Point point){
        abstractAirportFacade.create(point, emA);
    }

    @Override
    public void editPoint(Point point){
        abstractAirportFacade.edit(point, emA);
    }

    @Override
    public void deletePoint(Point point){
        abstractAirportFacade.remove(point, emA);
    }
}
