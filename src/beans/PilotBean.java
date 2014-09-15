package beans;

import views.PilotEJBBeanLocal;
import model.Pilot;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PilotBean implements Serializable {

    @EJB
    private PilotEJBBeanLocal pilotEJBBean;

    private List<Pilot> listOfPilots;

    private int selectedCategory;

    private Pilot selectedPilot;

    private List<Integer> categories;

    @Size(min = 2, max = 30, message = "Фамилия должна быть от 2 до 30 символов")
    private String surname;

    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    private String name;

    @Size(min = 2, max = 30, message = "Отчество должно быть от 2 до 30 символов")
    private String patronymic;

    @Size(min = 10, max = 100, message = "Адрес должен быть от 10 до 100 символов")
    private String address;

    @Size(min = 2, max = 11, message = "Номер телефона должен быть от 2 до 11 символов")
    private String phonenumber;

    public PilotBean(){
        categories = new ArrayList<Integer>();
        categories.add(new Integer(1));
        categories.add(new Integer(2));
    }

    public List<Pilot> getListOfPilots() {
        listOfPilots = pilotEJBBean.getListOfPilots();
        return listOfPilots;
    }

    public String addPilot(){
        Pilot pilot = new Pilot(surname, name, patronymic, phonenumber, address, selectedCategory);
        pilotEJBBean.addPilot(pilot);
        return "showPilots";
    }

    public String goToShowPilot(Pilot pilot){
        selectedPilot = pilot;
        return "showInfoAboutPilot";
    }

    public String goToDeletePilot(Pilot pilot){
        selectedPilot = pilot;
        return "confirmDeletingPilot";
    }

    public String confirmDeletePilot(){
        pilotEJBBean.deletePilot(selectedPilot);
        return "showPilots";
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public int getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(int selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Pilot getSelectedPilot() {
        return selectedPilot;
    }

    public void setSelectedPilot(Pilot selectedPilot) {
        this.selectedPilot = selectedPilot;
    }
}
