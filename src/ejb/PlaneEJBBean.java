package ejb;

import model.Pilot;
import model.Plane;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PlaneEJBBean implements PlaneEJBBeanLocal{

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    public PlaneEJBBean(){}

    @Override
    public List<Plane> getListOfPlanes(){
        return abstractAirportFacade.findAll(emA, Plane.class);
    }

    @Override
    public void addPlane(Plane plane){
        abstractAirportFacade.create(plane, emA);
    }

    @Override
    public void deletePlane(Plane plane){
        abstractAirportFacade.remove(plane, emA);
    }
}
