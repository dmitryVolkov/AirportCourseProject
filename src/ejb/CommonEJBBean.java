package ejb;

import model.*;
import views.CommonEJBBeanLocal;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.List;

@Stateless
public class CommonEJBBean implements CommonEJBBeanLocal {

    @PersistenceContext(name = "persistence/airusers", unitName= "AirusersPersistenceUnit")
    private EntityManager emU;

    @PersistenceContext(name = "persistence/airreserving", unitName = "AirreservingPersistenceUnit")
    private EntityManager emR;

    @PersistenceContext(name = "persistence/airport", unitName= "AirportPersistenceUnit")
    private EntityManager emA;

    @Resource
    private SessionContext sessionContext;

    public CommonEJBBean(){}

    @EJB
    private AbstractAirportFacade abstractAirportFacade;

    @Override
    public Group getGroupByTitle(String title){
        CriteriaQuery<Group> criteriaQuery = emU.getCriteriaBuilder().createQuery(Group.class);
        Root roleRoot = criteriaQuery.from(Group.class);
        Predicate predicate1 = roleRoot.get("groupName").in(title);
        criteriaQuery.select(roleRoot).where(predicate1);
        Group clientRole = emU.createQuery(criteriaQuery).getSingleResult();
        return clientRole;
    }

    @Override
    public Discount getDiscountByRouteId(int routeId){
        CriteriaQuery<Discount> criteriaQuery = emR.getCriteriaBuilder().createQuery(Discount.class);
        Root discRoot = criteriaQuery.from(Discount.class);
        Predicate predicate1 = discRoot.get("route_id").in(routeId);
        criteriaQuery.select(discRoot).where(predicate1);
        List<Discount> discounts = emR.createQuery(criteriaQuery).getResultList();
        if (discounts.size() > 0){
            return discounts.get(0);
        } else {
            Discount discount = new Discount();
            discount.setPercentOfDiscount(0);
            return discount;
        }
    }

    @Override
    public Route findRouteByDiscountId(int discountId){
        Discount discount = emR.find(Discount.class, discountId);
        Route route = emA.find(Route.class, discount.getRoute_id());
        return route;
    }

    @Override
    public List<Discount> getListOfDiscounts(){
        return abstractAirportFacade.findAll(emR, Discount.class);
    }

    @Override
    public void addDiscount(Discount discount){
        abstractAirportFacade.create(discount, emR);
    }

    @Override
    public void deleteDiscount(Discount discount){
        abstractAirportFacade.remove(discount, emR);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean buyTicketAndUpdateBillstat(Ticket ticket){
        Flight flight = emA.find(Flight.class, ticket.getFlight().getId());
        ticket.setFlight(flight);
        emA.merge(ticket);
        User user = emU.find(User.class, ticket.getUserId());
        if (user.getBillstats().size() != 0){
            Billstat billstat = user.getBillstats().get(0);
            if (billstat.getCountOfMoney() > flight.getPrice()){
                int finalCount = billstat.getCountOfMoney() - flight.getPrice() + (flight.getPrice() / 10);
                billstat.setCountOfMoney(finalCount);
                billstat.setLastDate(new Date());
                billstat.setLastOperation("Покупка");
                int sum = flight.getPrice() - flight.getId() / 10;
                billstat.setLastSum(sum);
                emU.merge(billstat);
                return true;
            } else if (billstat.getCountOfMoney() < flight.getPrice()){
                sessionContext.setRollbackOnly();
                return false;
            }
        }
        return false;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean reserveTicket(Ticket ticket){
        Flight flight = emA.find(Flight.class, ticket.getFlight().getId());
        List<Ticket> listOfTickets = flight.getTickets();
        ticket.setFlight(flight);
        ticket = emA.merge(ticket);
        emA.flush();
        User user = emU.find(User.class, ticket.getUserId());
        Reserving reserving = new Reserving(new Date(), ticket.getId(), user.getUser_id());
        reserving = emR.merge(reserving);
        Discount currentDiscount = getDiscountByRouteId(flight.getRoute().getId());
        int percent = currentDiscount.getPercentOfDiscount();
        int finalPrice = flight.getPrice() - (flight.getPrice() / 100 * percent);
        Reservebill bill = new Reservebill(finalPrice, currentDiscount, reserving);
        emR.merge(bill);
        int counter = 0;

        for (Ticket tick : listOfTickets){
            if ((tick.getUserId() == user.getUser_id()) && (tick.getStatus().equals("Б"))){
                counter++;
            }
        }
        if (counter >= 4){
            sessionContext.setRollbackOnly();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Billstat getBillstatByUserId(int userId){
        User user = emU.find(User.class, userId);
        if (user.getBillstats().size() > 0)
            return user.getBillstats().get(0);
        else return new Billstat();
    }
}
