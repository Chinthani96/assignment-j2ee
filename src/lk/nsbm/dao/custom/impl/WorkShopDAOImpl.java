package lk.nsbm.dao.custom.impl;

import com.mongodb.client.FindIterable;
import lk.nsbm.dao.CrudDAOImpl;
import lk.nsbm.dao.custom.WorkShopDAO;
import lk.nsbm.entity.User;
import lk.nsbm.entity.WorkShop;
import lk.nsbm.shared.BSONConverter;
import lk.nsbm.shared.enums.NonIdType;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class WorkShopDAOImpl extends CrudDAOImpl<WorkShop, Integer> implements WorkShopDAO {
    @Override
    public List<WorkShop> findByCreatedUser(User user) {
        List<WorkShop> workShopList = new ArrayList<>();
        Document userDoc = BSONConverter.getDocForSingleVal(user, WorkShop.class, NonIdType.TYPE_ONE);

        FindIterable<Document> documents = getCollection().find(userDoc);

        for (Document document :
                documents) {
            workShopList.add((WorkShop) BSONConverter.getObject(document, WorkShop.class));
        }

        return workShopList;
    }
}
