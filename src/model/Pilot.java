package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
public class Pilot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String address;

	private String name;

	private String patronymic;

	private String phonenumber;

	private String surname;

    private int category;

	@OneToMany(mappedBy="pilot")
	private List<Flight> flights;

	public Pilot() {
	}

    public Pilot(String surname, String name, String patronymic, String phonenumber, String address, int category) {
        this.surname = surname;
        this.address = address;
        this.name = name;
        this.patronymic = patronymic;
        this.phonenumber = phonenumber;
        this.category = category;
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronymic() {
		return this.patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}