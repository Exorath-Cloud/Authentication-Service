package exorath.cloud.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Connor on 12/14/2016.
 */
public class DatabaseProvider {

    MongoClient mongoClient;
    DB db;

    DatabaseProvider (MongoCredential credential){
        mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
        db = mongoClient.getDB(System.getenv("MONGO_DATABASE "));
    }

    public DBObject getDBObject(Object object){
        Gson gson = new GsonBuilder().create();
        return (DBObject) JSON.parse(gson.toJson(object));
    }

    public Object toPOJO(DBObject dbObject, Class clazz){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(JSON.serialize(dbObject),clazz);
    }

    public void updateUserData(UserData userData){
        DBCollection collection =  db.getCollection("users");
        collection.insert(getDBObject(userData));
    }

    public List<DBObject> getAllUsers(){
        DBCollection collection =  db.getCollection("users");
        return collection.find().toArray();
    }

}
