package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int percentOfDiscount;

    @Size(min = 0, max = 40, message = "Название может быть от 2 до 40")
	private String title;

    private int route_id;

	@OneToMany(mappedBy="discount")
	private List<Reservebill> reservebills;

	public Discount() {
	}

    public Discount(int percentOfDiscount, String title, int routeId){
        this.percentOfDiscount = percentOfDiscount;
        this.title = title;
        this.route_id = routeId;
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

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }
}