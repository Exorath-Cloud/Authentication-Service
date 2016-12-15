package exorath.cloud.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Connor on 12/14/2016.
 */
public class DatabaseProvider {

    MongoClient mongoClient;
    MongoDatabase db;

    DatabaseProvider (MongoCredential credential){
        mongoClient = new MongoClient(new ServerAddress(System.getenv("MONGO_HOST")), Arrays.asList(credential));
        db = mongoClient.getDatabase(System.getenv("MONGO_DATABASE"));
    }

    public Document getDBObject(Object object){
        Gson gson = new GsonBuilder().create();
        return (Document) JSON.parse(gson.toJson(object));
    }

    public Object toPOJO(Document dbObject, Class clazz){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(JSON.serialize(dbObject),clazz);
    }

    public void updateUserData(UserData userData){
        MongoCollection<Document> collection =  db.getCollection("users");
        collection.insertOne(getDBObject(userData));
    }

    public List<Document> getAllUsers(){
        List<Document> documents = new ArrayList<>();
        MongoCollection<Document> collection =  db.getCollection("users");
        Iterator<Document> iterator = collection.find().iterator();
        while(iterator.hasNext()){
            documents.add(iterator.next());
        }
        return documents;
    }

}
