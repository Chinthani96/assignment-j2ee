package lk.nsbm.service.custom.impl;

import lk.nsbm.dao.DAOFactory;
import lk.nsbm.dao.custom.CountDAO;
import lk.nsbm.entity.Count;
import lk.nsbm.service.custom.CountService;
import lk.nsbm.shared.enums.CountTypes;

public class CountServiceImpl implements CountService {

    private static CountServiceImpl countService;
    private CountDAO countDAO;

    private CountServiceImpl() {

        countDAO = (CountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COUNT);

    }

    public static CountService getInstance() {
        if (countService == null) {
            countService = new CountServiceImpl();
        }

        return countService;
    }

    @Override
    public synchronized int getCount(CountTypes countTypes) {
        Count countEntity = countDAO.findById(countTypes.getLabel());

        if (countEntity == null) {
            countEntity = countDAO.save(new Count(countTypes.getLabel(), 0));
        }

        countEntity.setSeq(countEntity.increaseSeq());
        Count save = countDAO.update(countEntity, countEntity.getTableFieldName());

        return save.getSeq();
    }
}
