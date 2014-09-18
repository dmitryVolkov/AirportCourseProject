package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int place;

	private int row;

	private String status;

	@Column(name="user_id")
	private int userId;

	@ManyToOne
	private Flight flight;

	public Ticket() {
	}

    public Ticket(int row, int place, String status, int userId, Flight flight){
        this.row = row;
        this.place = place;
        this.status = status;
        this.userId = userId;
        this.flight = flight;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlace() {
		return this.place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}