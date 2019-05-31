package lk.nsbm.dao;

import java.util.List;

public interface CrudDAO <Entity , PK> extends SuperDAO{
    Entity save(Entity entity);
    Entity findById(PK primary_key);
    List<Entity> findAll();
    Entity update(Entity entity , PK primaryKey);
    boolean delete(Entity entity);

}
