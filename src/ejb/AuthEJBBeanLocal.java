package ejb;

import model.User;

import javax.ejb.Local;

@Local
public interface AuthEJBBeanLocal {
    public User findUserByLogin(String login, String password);
    public String getHash(String str);
}
