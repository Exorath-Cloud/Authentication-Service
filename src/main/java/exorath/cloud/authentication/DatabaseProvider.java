package exorath.cloud.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
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

    public UserData getUserData(String username){
        return null;
    }

    public void addUserData(UserData userData){
        Gson gson = new GsonBuilder().create();
        db.getCollection("users").insertOne(Document.parse(gson.toJson(userData)));
    }

    public List<Document> getAllUsers() {
        ArrayList<Document> documents = new ArrayList<>();
        Iterator<Document> iterator = db.getCollection("users").find().iterator();
        while (iterator.hasNext()){
            documents.add(iterator.next());
        }
        return documents;
    }
}
