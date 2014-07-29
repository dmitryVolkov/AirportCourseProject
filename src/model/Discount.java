package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int percentOfDiscount;

	private String title;

	@OneToMany(mappedBy="discount")
	private List<Reservebill> reservebills;

	public Discount() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPercentOfDiscount() {
		return this.percentOfDiscount;
	}

	public void setPercentOfDiscount(int percentOfDiscount) {
		this.percentOfDiscount = percentOfDiscount;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Reservebill> getReservebills() {
		return this.reservebills;
	}

	public void setReservebills(List<Reservebill> reservebills) {
		this.reservebills = reservebills;
	}
}