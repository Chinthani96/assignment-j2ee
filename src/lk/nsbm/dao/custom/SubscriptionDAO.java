package lk.nsbm.dao.custom;

import lk.nsbm.dao.CrudDAO;
import lk.nsbm.entity.Subscription;
import lk.nsbm.entity.Subscription_PK;
import lk.nsbm.entity.User;

import java.util.List;

public interface SubscriptionDAO extends CrudDAO<Subscription, Subscription_PK> {
    List<Subscription> getUsersSubscribedList(User user);

}
