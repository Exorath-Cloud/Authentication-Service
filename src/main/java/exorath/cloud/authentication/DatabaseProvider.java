package exorath.cloud.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
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

    DatabaseProvider(MongoCredential credential) {
        mongoClient = new MongoClient(new ServerAddress(System.getenv("MONGO_HOST")), Arrays.asList(credential));
        db = mongoClient.getDatabase(System.getenv("MONGO_DATABASE"));
    }

    public UserData getUserData(String username) {
        return null;
    }

    public void addUserData(UserData userData) {
    }

    public List<UserData> getAllUsers() {
        return null;
    }
}
