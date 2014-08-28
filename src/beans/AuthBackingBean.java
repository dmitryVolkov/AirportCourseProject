package beans;

import ejb.AuthEJBBeanLocal;
import model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@ManagedBean
@SessionScoped
public class AuthBackingBean {

    private static Logger log = Logger.getLogger(AuthBackingBean.class.getName());

    private User userOnline = null;

    private String login;

    private String password;

    @EJB
    private AuthEJBBeanLocal authEJB;

    public String loginToSite(){
        userOnline = authEJB.findUserByLogin(login, password);
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            origRequest.login(login, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "../login/login.xhtml";
    }

    public String logout() {
        String result="/index?faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            result = "/loginError?faces-redirect=true";
        }
        userOnline = null;
        return result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUserOnline() {
        return userOnline;
    }
}
