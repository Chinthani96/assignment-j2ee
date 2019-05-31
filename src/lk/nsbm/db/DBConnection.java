package lk.nsbm.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBConnection {

    public static MongoDatabase getMongoDatabase() {

        MongoClient mongoClient = new MongoClient("localhost" , 27017);

        return mongoClient.getDatabase("endustrydb");

    }

}
