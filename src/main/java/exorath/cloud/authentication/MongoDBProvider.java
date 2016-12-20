package exorath.cloud.authentication;

import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import exorath.cloud.authentication.data.UserData;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Connor on 12/14/2016.
 */
public class MongoDBProvider implements DatabaseProvider{

    MongoClient mongoClient;
    MongoDatabase db;

    MongoDBProvider(ServerAddress serverAddress, MongoCredential credential, String database) {
        mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        db = mongoClient.getDatabase(database);
    }

    public UserData getUserDataByUsername(String username) {
        Document document = db.getCollection("users").find(new Document("username", username)).first();
        if (document != null) {
            UserData userData = new GsonBuilder().create().fromJson(document.toJson(), UserData.class);
            return userData;
        }
        return null;
    }

    public UserData getUserDataByEmail(String email) {
        Document document = db.getCollection("users").find(new Document("email", email)).first();
        if (document != null) {
            UserData userData = new GsonBuilder().create().fromJson(document.toJson(), UserData.class);
            return userData;
        }
        return null;
    }

    public UserData getUserData(String email, String username) {
        UserData userData = getUserDataByUsername(username);
        if (userData == null) {
            userData = getUserDataByEmail(email);
        }
        return userData;
    }

    public void addUserData(UserData userData) {
        if (getUserData(userData.getEmail(), userData.getUsername()) != null) {
            updateUserByUsername(userData.getUsername(), userData);
        } else {
            Document document = Document.parse(new GsonBuilder().create().toJson(userData));
            db.getCollection("users").insertOne(document);
        }
    }

    public List<UserData> getAllUsers() {
        ArrayList<UserData> userDatas = new ArrayList<>();
        MongoCursor<Document> cursor = db.getCollection("users").find().iterator();
        try {
            while (cursor.hasNext()) {
                userDatas.add(new GsonBuilder().create().fromJson(cursor.next().toJson(), UserData.class));
            }
        } finally {
            cursor.close();
        }
        return userDatas;
    }

    public void updateUserByUsername(String username, UserData userData) {
        db.getCollection("users").findOneAndReplace(new Document("username", username), Document.parse(new GsonBuilder().create().toJson(userData)));
    }

    public void updateUserByEmail(String email, UserData userData) {
        db.getCollection("users").findOneAndReplace(new Document("email", email), Document.parse(new GsonBuilder().create().toJson(userData)));
    }
}
