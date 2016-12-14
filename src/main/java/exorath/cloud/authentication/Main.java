package exorath.cloud.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.rmi.UnknownHostException;

/**
 * Created by Connor on 12/14/2016.
 */
public class Main {

    public static void main(String[] args) {
        Service service = new Service();

        try {

            //testing java object to mongo object
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            DB db = mongoClient.getDB("auth");
            Gson gson = new GsonBuilder().create();
            DBCollection collection = db.getCollection("users");
            DBObject dbObject = (DBObject) JSON
                    .parse(gson.toJson(new UserData()));
            collection.insert(dbObject);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
