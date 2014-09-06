package ejb;

import model.Billstat;
import model.Group;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Stateless
public class AuthEJBBean implements AuthEJBBeanLocal, Serializable {

    @PersistenceContext(name = "persistence/airusers", unitName= "AirusersPersistenceUnit")
    private EntityManager emU;

    @EJB
    private CommonEJBBeanLocal commonEJBBean;

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
        if (str == null || str.equals(""))
            return str;
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

    /**
     * Поиск пользователя по логину (для валидации)
     * @param login - логин
     * @return
     */
    @Override
    public boolean checkUserWithSameLogin(String login){
        CriteriaQuery<User> criteriaQuery = emU.getCriteriaBuilder().createQuery(User.class);
        Root userRoot = criteriaQuery.from(User.class);
        Predicate predicate1 = userRoot.get("username").in(login);
        criteriaQuery.select(userRoot).where(predicate1);
        List<User> listOfUsers = emU.createQuery(criteriaQuery).getResultList();
        if (listOfUsers.size() != 0)
            return true;
        return false;
    }


    /**
     * Зарегистрировать нового клиента
     * @param user - клиент
     */
    @Override
    public void registerNewClient(User user){
        user.setPassword(getHash(user.getPassword()));
        Group clientGroup = commonEJBBean.getGroupByTitle("USER");
        user = emU.merge(user);
        user.getGroups().add(clientGroup);
        clientGroup.getUsers().add(user);
        Billstat billstat = new Billstat(0, new Date(), String.valueOf(generateNumberOfBill()),
                "Инициализация", 0);
        user.getBillstats().add(billstat);
        billstat.setUser(user);
        emU.merge(user);
    }

    private int generateNumberOfBill(){
        int low = 100000000;
        int high = 999999999;
        Random random = new Random();
        int result = random.nextInt(high - low) + low;
        return result;
    }
}
