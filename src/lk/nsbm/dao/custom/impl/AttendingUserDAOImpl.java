package lk.nsbm.dao.custom.impl;

import com.mongodb.client.FindIterable;
import lk.nsbm.dao.CrudDAOImpl;
import lk.nsbm.dao.custom.AttendingUserDAO;
import lk.nsbm.entity.AttendingUser;
import lk.nsbm.shared.BSONConverter;
import lk.nsbm.shared.enums.NonIdType;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class AttendingUserDAOImpl extends CrudDAOImpl<AttendingUser, Integer> implements AttendingUserDAO {
    @Override
    public List<AttendingUser> getUsersOfEvent(int workshopId) {
        List<AttendingUser> attendingUserList = new ArrayList<>();
        Document workShopIdDco = BSONConverter.getDocForSingleVal(workshopId, AttendingUser.class, NonIdType.TYPE_ONE);

        FindIterable<Document> documents = getCollection().find(workShopIdDco);

        for (Document document :
                documents) {
            attendingUserList.add((AttendingUser) BSONConverter.getObject(document, AttendingUser.class));
        }

        return attendingUserList;
    }
}
