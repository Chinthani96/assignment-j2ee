package lk.nsbm.entity;

import lk.nsbm.shared.annotations.NonIdValue;
import lk.nsbm.shared.enums.NonIdType;

public class Subscription_PK {

    @NonIdValue(type = NonIdType.TYPE_ONE , name = "subscribed_user")
    private User subscribed_user;
    private User subscription_user;

    public Subscription_PK() {
    }

    public Subscription_PK(User subscribed_user, User subscription_user) {
        this.subscribed_user = subscribed_user;
        this.subscription_user = subscription_user;
    }

    public User getSubscribed_user() {
        return subscribed_user;
    }

    public void setSubscribed_user(User subscribed_user) {
        this.subscribed_user = subscribed_user;
    }

    public User getSubscription_user() {
        return subscription_user;
    }

    public void setSubscription_user(User subscription_user) {
        this.subscription_user = subscription_user;
    }
}
