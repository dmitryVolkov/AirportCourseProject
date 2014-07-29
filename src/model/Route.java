package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int distance;

	private String pointOfArrival;

	private String pointOfDeparture;

	private String title;

	@OneToMany(mappedBy="route")
	private List<Flight> flights;

	public Route() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getPointOfArrival() {
		return this.pointOfArrival;
	}

	public void setPointOfArrival(String pointOfArrival) {
		this.pointOfArrival = pointOfArrival;
	}

	public String getPointOfDeparture() {
		return this.pointOfDeparture;
	}

	public void setPointOfDeparture(String pointOfDeparture) {
		this.pointOfDeparture = pointOfDeparture;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
}