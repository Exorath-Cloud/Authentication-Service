package exorath.cloud.authentication;

import com.mongodb.MongoCredential;

/**
 * Created by Connor on 12/14/2016.
 */
public class Main {

    static Service service;
    static DatabaseProvider databaseProvider;

    public static void main(String[] args) {

        service = new Service();
        databaseProvider = new DatabaseProvider(MongoCredential.createCredential(System.getenv("MONGO_USER"), System.getenv("MONGO_DATABASE"), System.getenv("MONGO_PWD").toCharArray()));
    }

}
