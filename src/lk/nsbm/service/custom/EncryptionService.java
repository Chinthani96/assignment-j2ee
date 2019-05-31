package lk.nsbm.service.custom;

import lk.nsbm.entity.User;
import lk.nsbm.service.SuperService;

public interface EncryptionService extends SuperService {

    boolean isDataEqualsWithEncryption(String data, User user);

    User generateEncryptionValue(User user);
}
