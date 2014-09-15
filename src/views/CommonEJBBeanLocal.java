package views;

import model.Group;

import javax.ejb.Local;

@Local
public interface CommonEJBBeanLocal {
    public Group getGroupByTitle(String title);
}
