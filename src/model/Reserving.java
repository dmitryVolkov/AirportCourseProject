package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Reserving implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfReserve;

	@Column(name="ticket_id")
	private int ticketId;

	@Column(name="user_id")
	private int userId;

	@OneToOne(mappedBy="reserving")
	private Reservebill reservebill;

	public Reserving() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfReserve() {
		return this.dateOfReserve;
	}

	public void setDateOfReserve(Date dateOfReserve) {
		this.dateOfReserve = dateOfReserve;
	}

	public int getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Reservebill getReservebill() {
		return this.reservebill;
	}

	public void setReservebills(Reservebill reservebill) {
		this.reservebill = reservebill;
	}
}