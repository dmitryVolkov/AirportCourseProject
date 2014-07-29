package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Reservebill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int finalSum;

	private int prepay;

	private int price;

	@ManyToOne
	private Discount discount;

	@OneToOne
	private Reserving reserving;

	public Reservebill() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFinalSum() {
		return this.finalSum;
	}

	public void setFinalSum(int finalSum) {
		this.finalSum = finalSum;
	}

	public int getPrepay() {
		return this.prepay;
	}

	public void setPrepay(int prepay) {
		this.prepay = prepay;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Discount getDiscount() {
		return this.discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Reserving getReserving() {
		return this.reserving;
	}

	public void setReserving(Reserving reserving) {
		this.reserving = reserving;
	}

}