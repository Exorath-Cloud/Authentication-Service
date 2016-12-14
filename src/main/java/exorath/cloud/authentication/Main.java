package exorath.cloud.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.Arrays;

/**
 * Created by Connor on 12/14/2016.
 */
public class Main {

    public static void main(String[] args) {
        Service service = new Service();

        try {

            //testing java object to mongo object
            MongoCredential credential = MongoCredential.createCredential(System.getenv("MONGO_HOST"), System.getenv("MONGO_USER "), System.getenv("MONGO_PWD").toCharArray());
            MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
            DB db = mongoClient.getDB(System.getenv("MONGO_DATABASE "));
            DBCollection collection = db.getCollection("users");
            Gson gson = new GsonBuilder().create();
            DBObject dbObject = (DBObject) JSON.parse(gson.toJson(new UserData()));
            collection.insert(dbObject);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
