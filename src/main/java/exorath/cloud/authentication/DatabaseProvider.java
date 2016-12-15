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

    DatabaseProvider(ServerAddress serverAddress,MongoCredential credential, String database) {
        mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        db = mongoClient.getDatabase(database);
    }

    public UserData getUserData(String username) {
        Document document = db.getCollection("users").find(new Document("username",username)).first();
        UserData userData = new GsonBuilder().create().fromJson(document.toJson(),UserData.class);
        return userData;
    }

    public void addUserData(UserData userData) {
        Document document = Document.parse(new GsonBuilder().create().toJson(userData));
        db.getCollection("users").insertOne(document);
    }

    public List<UserData> getAllUsers() {
        return null;
    }
}
