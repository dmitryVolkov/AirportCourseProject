package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timeOfArrival;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timeOfDeparture;

	@ManyToOne
	private Pilot pilot;

	@ManyToOne
	private Route route;

	@ManyToOne
	private Plane plane;

	@OneToMany(mappedBy="flight")
	private List<Ticket> tickets;

	public Flight() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimeOfArrival() {
		return this.timeOfArrival;
	}

	public void setTimeOfArrival(Date timeOfArrival) {
		this.timeOfArrival = timeOfArrival;
	}

	public Date getTimeOfDeparture() {
		return this.timeOfDeparture;
	}

	public void setTimeOfDeparture(Date timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}

	public Pilot getPilot() {
		return this.pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Plane getPlane() {
		return this.plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}