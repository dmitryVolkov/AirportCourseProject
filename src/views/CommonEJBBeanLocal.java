package views;

import model.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CommonEJBBeanLocal {
    public Group getGroupByTitle(String title);
    public List<Discount> getListOfDiscounts();
    public void addDiscount(Discount discount);
    public void deleteDiscount(Discount discount);
    public Route findRouteByDiscountId(int discountId);
    public Discount getDiscountByRouteId(int routeId);
    public boolean buyTicketAndUpdateBillstat(Ticket ticket);
    public Billstat getBillstatByUserId(int userId);
    public boolean reserveTicket(Ticket ticket);
}
