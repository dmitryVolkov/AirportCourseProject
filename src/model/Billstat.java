package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the billstat database table.
 * 
 */
@Entity
@NamedQuery(name="Billstat.findAll", query="SELECT b FROM Billstat b")
public class Billstat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int countOfMoney;

	@Temporal(TemporalType.DATE)
	private Date currentDate;

	private String number;

	private String operation;

	private int sum;

	@Column(name="ticket_id")
	private int ticketId;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Billstat() {
	}

    public Billstat(int countOfMoney, Date currentDate, String number, String operation,
                    int sum){
        this.countOfMoney = countOfMoney;
        this.currentDate = currentDate;
        this.number = number;
        this.operation = operation;
        this.sum = sum;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountOfMoney() {
		return this.countOfMoney;
	}

	public void setCountOfMoney(int countOfMoney) {
		this.countOfMoney = countOfMoney;
	}

	public Date getCurrentDate() {
		return this.currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getSum() {
		return this.sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}