package exorath.cloud.authentication;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import exorath.cloud.authentication.data.DatabaseProvider;

/**
 * Created by Connor on 12/14/2016.
 */
public class Main {

    public static Service service;
    public static DatabaseProvider databaseProvider;

    public static void main(String[] args) {
        service = new Service(Integer.parseInt(System.getenv("PORT")));
        String host = System.getenv("MONGO_HOST");
        String username = System.getenv("MONGO_USER");
        String database = System.getenv("MONGO_DATABASE");
        String password = System.getenv("MONGO_PWD");
        databaseProvider = new MongoDBProvider(new ServerAddress(host), MongoCredential.createCredential(username, database, password.toCharArray()), database);
    }

}
