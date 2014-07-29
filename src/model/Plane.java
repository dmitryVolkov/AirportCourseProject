package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
public class Plane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int countOfRows;

	private String model;

	private int placesInRow;

	private int year;

	@OneToMany(mappedBy="plane")
	private List<Flight> flights;

	public Plane() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountOfRows() {
		return this.countOfRows;
	}

	public void setCountOfRows(int countOfRows) {
		this.countOfRows = countOfRows;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPlacesInRow() {
		return this.placesInRow;
	}

	public void setPlacesInRow(int placesInRow) {
		this.placesInRow = placesInRow;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
}