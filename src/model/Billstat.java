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
	private Date lastDate;

	private String number;

	private String lastOperation;

	private int lastSum;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Billstat() {
	}

    public Billstat(int countOfMoney, Date currentDate, String number, String operation,
                    int sum){
        this.countOfMoney = countOfMoney;
        this.lastDate = currentDate;
        this.number = number;
        this.lastOperation = operation;
        this.lastSum = sum;
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

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(String lastOperation) {
        this.lastOperation = lastOperation;
    }

    public int getLastSum() {
        return lastSum;
    }

    public void setLastSum(int lastSum) {
        this.lastSum = lastSum;
    }
}