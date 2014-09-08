package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the route database table.
 * 
 */
@Entity
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int distance;

	private String title;

	//bi-directional many-to-one association to Point
	@ManyToOne
	@JoinColumn(name="pointOfDeparture_id")
	private Point point1;

	//bi-directional many-to-one association to Point
	@ManyToOne
	@JoinColumn(name="pointOfArrival_id")
	private Point point2;

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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Point getPoint1() {
		return this.point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return this.point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

}