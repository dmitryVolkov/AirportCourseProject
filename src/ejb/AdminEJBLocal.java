package ejb;

import model.Pilot;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AdminEJBLocal {
    public List<Pilot> getListOfPilots();
    public void addPilot(Pilot pilot);
    public void deletePilot(Pilot pilot);
}
