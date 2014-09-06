package beans;

import ejb.AuthEJBBeanLocal;
import model.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

@Named
@RequestScoped
public class RegistrationBean implements Serializable {

    @EJB
    private AuthEJBBeanLocal authEJBBean;

    @Size(max = 10,min = 2,message = "Логин должен быть от 2 до 10 символов")
    private String userName;

    @Size(max = 15, min = 2, message = "Пароль должен быть от 2 до 15 символов")
    private String password;

    @Size(max = 15, min = 2, message = "Повторение пароля должно быть от 2 до 15 символов")
    private String repeatPassword;

    @Size(max = 20, min = 2, message = "Имя должно быть от 0 до 20 символов")
    private String firstName;

    @Size(max = 20, min = 2, message = "Отчество должно быть от 0 до 20 символов")
    private String middleName;

    @Size(max = 20, min = 2, message = "Фамилия должна быть от 0 до 20 символов")
    private String lastName;

    @Size(max = 11, min = 6, message = "Номер телефона должен быть от 6 до 11 символов")
    private String phoneNumber;

    @Size(max = 40, min = 4, message = "EMail должен быть от 4 до 40 символов")
    private String eMail;

    private Date dateOfBirth;

    public String register(){
        User user = new User(dateOfBirth, eMail, firstName, lastName, middleName, password, phoneNumber, userName);
        authEJBBean.registerNewClient(user);
        return "index";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void validatePassword(ComponentSystemEvent event)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? ""
                : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("repeatPass");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        if (!password.equals(confirmPassword))
        {
            FacesMessage msg = new FacesMessage("Пароль и повторение пароля должны совпадать");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();
        }
    }

    public void validateNoExistingOfLogin(ComponentSystemEvent event)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputLogin = (UIInput) components.findComponent("login");
        String login = uiInputLogin.getLocalValue() == null ? ""
                : uiInputLogin.getLocalValue().toString();
        String loginId = uiInputLogin.getClientId();
        if (authEJBBean.checkUserWithSameLogin(login))
        {
            FacesMessage msg = new FacesMessage("Пользователь с таким логином уже зарегистрирован");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(loginId, msg);
            fc.renderResponse();
        }
    }

    public void validateEMailMask(ComponentSystemEvent event){
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputEMail = (UIInput) components.findComponent("eMail");
        String eMail = uiInputEMail.getLocalValue() == null ? ""
                : uiInputEMail.getLocalValue().toString();
        String eMailId = uiInputEMail.getClientId();
        String[] eMailMas = eMail.split(Pattern.quote("@"));
        if (eMailMas.length != 2){
            FacesMessage msg = new FacesMessage("EMail должен соответствовать маске --@--.--");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(eMailId, msg);
            fc.renderResponse();
        } else if (eMailMas.length == 2){
            String[] secondPartMas = eMailMas[1].split(Pattern.quote("."));
            if (secondPartMas.length != 2){
                FacesMessage msg = new FacesMessage("EMail должен соответствовать маске --@--.--");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                fc.addMessage(eMailId, msg);
                fc.renderResponse();
            }
        }
    }

    public void validatePhoneNumberByDigit(ComponentSystemEvent event){
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputPhoneNumber = (UIInput) components.findComponent("phoneNumber");
        String phoneNumber = uiInputPhoneNumber.getLocalValue() == null ? ""
                : uiInputPhoneNumber.getLocalValue().toString();
        String phoneNumberID = uiInputPhoneNumber.getClientId();
        boolean isDigit = true;
        try {
            Long.parseLong(phoneNumber);
        } catch (Exception e){
            isDigit = false;
        }
        if (!isDigit){
            FacesMessage msg = new FacesMessage("Номер телефона должен состоять из цифр");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(phoneNumberID, msg);
            fc.renderResponse();
        }
    }

}
