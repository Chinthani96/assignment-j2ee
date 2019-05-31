package lk.nsbm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.annotations.NonIdValue;
import lk.nsbm.shared.enums.NonIdType;
import lk.nsbm.shared.enums.Subscription_Type;

import java.util.Date;

@JsonIgnoreProperties(value = {"_id"})
public class Subscription {

    @IdValue(name = "subscription_pk")
    private Subscription_PK subscription_pk;
    private Date subscribed_date;
    @NonIdValue(type = NonIdType.TYPE_TWO , name = "subscription_type")
    private Subscription_Type subscription_type;
    private String description;

    public Subscription() {
    }

    public Subscription(Subscription_PK subscription_pk, Date subscribed_date, Subscription_Type subscription_type, String description) {
        this.subscription_pk = subscription_pk;
        this.subscribed_date = subscribed_date;
        this.subscription_type = subscription_type;
        this.description = description;
    }

    public Subscription(User subscribed_user, User subscription_user, Date subscribed_date, Subscription_Type subscription_type, String description) {
        this.subscription_pk = new Subscription_PK(subscribed_user, subscription_user);
        this.subscribed_date = subscribed_date;
        this.subscription_type = subscription_type;
        this.description = description;
    }

    public Date getSubscribed_date() {
        return subscribed_date;
    }

    public void setSubscribed_date(Date subscribed_date) {
        this.subscribed_date = subscribed_date;
    }

    public Subscription_Type getSubscription_type() {
        return subscription_type;
    }

    public void setSubscription_type(Subscription_Type subscription_type) {
        this.subscription_type = subscription_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subscription_PK getSubscription_pk() {
        return subscription_pk;
    }

    public void setSubscription_pk(Subscription_PK subscription_pk) {
        this.subscription_pk = subscription_pk;
    }
}
