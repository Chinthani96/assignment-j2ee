package lk.nsbm.dto;

import lk.nsbm.entity.Subscription_PK;
import lk.nsbm.shared.enums.Subscription_Type;

import java.util.Date;

public class SubscriptionDTO {
    private Subscription_PK subscription_pk;
    private Date subscribed_date;
    private Subscription_Type subscription_type;
    private String description;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Subscription_PK subscription_pk, Date subscribed_date, Subscription_Type subscription_type, String description) {
        this.subscription_pk = subscription_pk;
        this.subscribed_date = subscribed_date;
        this.subscription_type = subscription_type;
        this.description = description;
    }

    public Subscription_PK getSubscription_pk() {
        return subscription_pk;
    }

    public void setSubscription_pk(Subscription_PK subscription_pk) {
        this.subscription_pk = subscription_pk;
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
}
