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

    static Service service;
    static DatabaseProvider databaseProvider;

    public static void main(String[] args) {

        service = new Service();
        databaseProvider = new DatabaseProvider(MongoCredential.createCredential(System.getenv("MONGO_HOST"), System.getenv("MONGO_DATABASE"), System.getenv("MONGO_PWD").toCharArray()));
    }

}
