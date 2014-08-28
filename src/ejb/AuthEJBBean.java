package ejb;

import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
public class AuthEJBBean implements AuthEJBBeanLocal {

    @PersistenceContext(name = "persistence/airusers", unitName= "AirusersPersistenceUnit")
    private EntityManager emU;

    public AuthEJBBean(){}

    /**
     * Поиск пользователя по логину и паролю
     * @param login - логин
     * @param password - пароль
     * @return - пользователь/null
     */
    @Override
    public User findUserByLogin(String login, String password){
        CriteriaQuery<User> criteriaQuery = emU.getCriteriaBuilder().createQuery(User.class);
        Root userRoot = criteriaQuery.from(User.class);
        Predicate predicate1 = userRoot.get("username").in(login);
        Predicate predicate2 = userRoot.get("password").in(getHash(password));
        criteriaQuery.select(userRoot).where(predicate1);
        criteriaQuery.select(userRoot).where(predicate2);
        List<User> listOfUsers = emU.createQuery(criteriaQuery).getResultList();
        if (listOfUsers.size() != 0 )
            return listOfUsers.get(0);
        else return null;
    }

    /**
     * Шифрование MD5
     * @param sInput - строка, которую надо зашифровать
     * @return
     */
    public String getHash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
