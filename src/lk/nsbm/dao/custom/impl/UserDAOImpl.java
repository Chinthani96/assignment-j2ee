package lk.nsbm.dao.custom.impl;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import lk.nsbm.dao.CrudDAOImpl;
import lk.nsbm.dao.custom.UserDAO;
import lk.nsbm.entity.User;
import lk.nsbm.shared.BSONConverter;
import org.bson.Document;

import java.util.Date;

public class UserDAOImpl extends CrudDAOImpl<User, String> implements UserDAO {
    @Override
    public int updateLoginAttempts(String username, int type) {
        User userEntity = findById(username);

        User userCloned = findById(username);
        /*User userCloned = null;
        try {
            userCloned = (User) userEntity.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }*/

        if (type == 1) {
            userCloned.setLoginAttempts(userCloned.getLoginAttempts() + 1);
        } else {
            userCloned.setLoginAttempts(0);
            userCloned.setLastLoginDate(new Date());
        }

        Document userEntityDoc = BSONConverter.getDocument(userEntity);
        Document clonedEntityDoc = BSONConverter.getDocument(userCloned);
        UpdateResult updateResult = getCollection().replaceOne(userEntityDoc, clonedEntityDoc,new UpdateOptions().upsert(true));

        if(updateResult.isModifiedCountAvailable()) {
            return userCloned.getLoginAttempts();
        }

        return -1;
    }

    @Override
    public boolean isAvailable(String username) {
        User user = this.findById(username);

        if (user == null) {
            return false;
        }

        return true;
    }
}
