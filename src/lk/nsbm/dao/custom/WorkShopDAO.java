package lk.nsbm.dao.custom;

import lk.nsbm.dao.CrudDAO;
import lk.nsbm.entity.User;
import lk.nsbm.entity.WorkShop;

import java.util.List;

public interface WorkShopDAO extends CrudDAO<WorkShop , Integer> {
    List<WorkShop> findByCreatedUser(User user);
}
