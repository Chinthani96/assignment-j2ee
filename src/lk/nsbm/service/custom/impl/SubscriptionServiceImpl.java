package lk.nsbm.service.custom.impl;

import lk.nsbm.dao.DAOFactory;
import lk.nsbm.dao.custom.SubscriptionDAO;
import lk.nsbm.dao.custom.UserDAO;
import lk.nsbm.dto.SubscriptionDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.entity.Subscription;
import lk.nsbm.entity.Subscription_PK;
import lk.nsbm.entity.User;
import lk.nsbm.service.custom.SubscriptionService;
import lk.nsbm.shared.enums.Subscription_Type;
import lk.nsbm.shared.enums.Update_Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionDAO subscriptionDAO;
    private UserDAO userDAO;


    public SubscriptionServiceImpl() {
        subscriptionDAO = (SubscriptionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUBSCRIPTION);
        userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    }

    @Override
    public Update_Status subscribeUser(UserDTO subscribed_user, UserDTO subscription_user) {
        User subscribedUserEntity = new User();
        User subscriptionUserEntity = new User();

        if (!userDAO.isAvailable(subscribed_user.getUsername()) || !userDAO.isAvailable(subscription_user.getUsername())) {
            return Update_Status.NOT_FOUND;
        }

        subscribedUserEntity.setUsername(subscribed_user.getUsername());
        subscriptionUserEntity.setUsername(subscription_user.getUsername());

        if (subscribedUserEntity == null) {
            return Update_Status.INVALID_CREDENTIALS;
        } else if (subscriptionUserEntity == null) {
            return Update_Status.NOT_FOUND;
        }

        Subscription subscriptionEntity = subscriptionDAO.findById(new Subscription_PK(subscribedUserEntity,
                subscriptionUserEntity));

        if (subscriptionEntity == null) {
            Subscription savedSubscription = subscriptionDAO.save(new Subscription(new Subscription_PK(
                    subscribedUserEntity, subscriptionUserEntity), new Date(), Subscription_Type.INDUSTRY, ""));

            if (savedSubscription == null) {
                return Update_Status.FAILED;
            }

            return Update_Status.SUCCESS;
        } else {
            boolean isDeleted = subscriptionDAO.delete(subscriptionEntity);
            if (isDeleted) {
                return Update_Status.SUCCESS;
            }

            return Update_Status.FAILED;
        }
    }

    @Override
    public Update_Status addStudiedUser(UserDTO subscribed_user, UserDTO subscription_user, String description) {
        User subscribedUserEntity = userDAO.findById(subscribed_user.getUsername());
        User subscriptionUserEntity = userDAO.findById(subscription_user.getUsername());

        if (subscribedUserEntity == null) {
            return Update_Status.INVALID_CREDENTIALS;
        } else if (subscriptionUserEntity == null) {
            return Update_Status.NOT_FOUND;
        }

        Subscription subscriptionEntity = subscriptionDAO.findById(new Subscription_PK(subscribedUserEntity,
                subscriptionUserEntity));

        if (subscriptionEntity == null) {
            Subscription savedSubscription = subscriptionDAO.save(new Subscription(new Subscription_PK(
                    subscribedUserEntity, subscriptionUserEntity), new Date(), Subscription_Type.STUDIED, description));

            if (savedSubscription == null) {
                return Update_Status.FAILED;
            }

            return Update_Status.SUCCESS;
        } else {
            boolean isDeleted = subscriptionDAO.delete(subscriptionEntity);
            if (isDeleted) {
                return Update_Status.SUCCESS;
            }

            return Update_Status.FAILED;
        }
    }

    @Override
    public List<SubscriptionDTO> getSubscriptionListOfUser(UserDTO subscribed_user) {
        List<SubscriptionDTO> subscriptionDTOSList = new ArrayList<>();

        if (!userDAO.isAvailable(subscribed_user.getUsername())) {
            return null;
        }

        User userEntity = new User();
        userEntity.setUsername(subscribed_user.getUsername());

        List<Subscription> usersSubscribedList = subscriptionDAO.getUsersSubscribedList(userEntity);

        for (Subscription subscription :
                usersSubscribedList) {
            subscriptionDTOSList.add(new SubscriptionDTO(subscription.getSubscription_pk(), subscription.getSubscribed_date(), subscription.getSubscription_type(), subscription.getDescription()));
        }
        return subscriptionDTOSList;
    }
}
