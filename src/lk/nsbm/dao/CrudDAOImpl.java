package lk.nsbm.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lk.nsbm.db.DBConnection;
import lk.nsbm.shared.BSONConverter;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class CrudDAOImpl<Entity, PK> implements CrudDAO<Entity, PK> {

    private Class<Entity> entityClass;
    private Class<PK> primayKeyClass;
    private String entityName;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;

    public CrudDAOImpl() {
        entityClass = (Class<Entity>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String entityName = entityClass.getName();
        String[] split = entityName.split("\\.");
        entityName = split[split.length - 1].toLowerCase();
        mongoDatabase = DBConnection.getMongoDatabase();
        collection = mongoDatabase.getCollection(entityName);
    }

    @Override
    public Entity update(Entity entity, PK primaryKey) {
        Bson pkDoc = BSONConverter.getDocForSingleVal(primaryKey , entityClass);
        Document entityDoc = BSONConverter.getDocument(entity);

        UpdateResult result = getCollection().replaceOne(pkDoc, entityDoc, new UpdateOptions().upsert(true));

        if (result.getModifiedCount() > 0) {
            return this.findById(primaryKey);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Entity entity) {
        Bson entityDoc = BSONConverter.getDocument(entity);
        DeleteResult deleteResult = getCollection().deleteOne(entityDoc);

        return deleteResult.getDeletedCount() > 0;
    }

    @Override
    public Entity save(Entity entity) {
        getCollection().insertOne(BSONConverter.getDocument(entity));

        return entity;
    }

    @Override
    public Entity findById(PK primary_key) {
        FindIterable<Document> documents = getCollection().find(BSONConverter.getDocForSingleVal(primary_key,entityClass));

        for (Document doc :
                documents) {
            return (Entity) BSONConverter.getObject(doc, entityClass);
        }

        return null;
    }

    @Override
    public List<Entity> findAll() {
        List<Entity> entities = new ArrayList<>();
        FindIterable<Document> documents = getCollection().find();

        for (Document doc :
                documents) {
            Entity entity = (Entity) BSONConverter.getObject(doc, entityClass);
            entities.add(entity);
        }

        return entities;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

}
