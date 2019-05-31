package lk.nsbm.service.custom;

import lk.nsbm.dto.SubscriptionDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.service.SuperService;
import lk.nsbm.shared.enums.Subscription_Type;
import lk.nsbm.shared.enums.Update_Status;

import java.util.List;

public interface SubscriptionService extends SuperService {

    Update_Status subscribeUser(UserDTO subscribed_user, UserDTO subscription_user);

    Update_Status addStudiedUser(UserDTO subscribed_user, UserDTO subscription_user, String description);

    List<SubscriptionDTO> getSubscriptionListOfUser(UserDTO subscribed_user);
}
