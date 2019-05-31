package lk.nsbm.dao.custom;

import lk.nsbm.dao.CrudDAO;
import lk.nsbm.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    int updateLoginAttempts(String username, int type);

    boolean isAvailable(String username);
}
