package lk.nsbm.dao.custom;

import lk.nsbm.dao.CrudDAO;
import lk.nsbm.entity.AttendingUser;

import java.util.List;

public interface AttendingUserDAO extends CrudDAO<AttendingUser, Integer> {
    List<AttendingUser> getUsersOfEvent(int workshopId);
}
