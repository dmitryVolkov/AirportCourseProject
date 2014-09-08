package ejb;

import model.Pilot;
import model.Plane;
import model.Point;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AdminEJBBean implements AdminEJBLocal {

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    public AdminEJBBean(){}

    @Override
    public List<Pilot> getListOfPilots(){
        Query query = emA.createQuery("SELECT p FROM Pilot p");
        return query.getResultList();
    }

    @Override
    public List<Plane> getListOfPlanes(){
        Query query = emA.createQuery("SELECT p FROM Plane p");
        return query.getResultList();
    }

    @Override
    public List<Point> getListOfPoints(){
        Query query = emA.createQuery("SELECT p FROM Point p");
        return query.getResultList();
    }

    @Override
    public void addPilot(Pilot pilot){
        emA.persist(pilot);
    }

    @Override
    public void addPlane(Plane plane){
        emA.persist(plane);
    }

    @Override
    public void addPoint(Point point){
        emA.persist(point);
    }

    @Override
    public void editPoint(Point point){
        emA.merge(point);
    }

    @Override
    public void deletePilot(Pilot pilot){
        pilot = emA.merge(pilot);
        emA.remove(pilot);
    }

    @Override
    public void deletePlane(Plane plane){
        plane = emA.merge(plane);
        emA.remove(plane);
    }

    @Override
    public void deletePoint(Point point){
        point = emA.merge(point);
        emA.remove(point);
    }
}
