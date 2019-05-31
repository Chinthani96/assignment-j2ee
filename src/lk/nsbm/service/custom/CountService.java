package lk.nsbm.service.custom;

import lk.nsbm.service.SuperService;
import lk.nsbm.shared.enums.CountTypes;

public interface CountService extends SuperService {

    int getCount(CountTypes countTypes);

}
