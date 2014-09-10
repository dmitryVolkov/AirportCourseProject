package ejb;

import model.Pilot;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PilotEJBBean implements PilotEJBBeanLocal {

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    @Override
    public List<Pilot> getListOfPilots(){
        return abstractAirportFacade.findAll(emA, Pilot.class);
    }

    @Override
    public void addPilot(Pilot pilot){
        abstractAirportFacade.create(pilot, emA);
    }

    @Override
    public void deletePilot(Pilot pilot){
        abstractAirportFacade.remove(pilot, emA);
    }

}
