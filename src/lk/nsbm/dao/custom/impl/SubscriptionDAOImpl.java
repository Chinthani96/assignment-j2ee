package lk.nsbm.dao.custom.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import lk.nsbm.dao.CrudDAOImpl;
import lk.nsbm.dao.custom.SubscriptionDAO;
import lk.nsbm.entity.Subscription;
import lk.nsbm.entity.Subscription_PK;
import lk.nsbm.entity.User;
import lk.nsbm.shared.BSONConverter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOImpl extends CrudDAOImpl<Subscription, Subscription_PK> implements SubscriptionDAO {
    @Override
    public List<Subscription> getUsersSubscribedList(User user) {
        List<Subscription> subscriptionList = new ArrayList<>();
        String s = null;
        try {
            s = (new ObjectMapper()).writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        String subscribedUserJsonStr = BSONConverter.getStringJson(user, Subscription_PK.class, NonIdType.TYPE_ONE);
        String wordsJson = "{\"subscription_pk.subscribed_user\" : " + s + "}";
        Document parse = Document.parse(wordsJson);
//        Document subscriptionEntity = BSONConverter.getDocForSingleVal(subscribedUserJsonStr, Subscription.class);

        System.out.println("Checking this " + parse);

        FindIterable<Document> documents = getCollection().find(parse);

        for (Document doc :
                documents) {
            subscriptionList.add((Subscription) BSONConverter.getObject(doc, Subscription.class));
        }

        return subscriptionList;
    }
}
