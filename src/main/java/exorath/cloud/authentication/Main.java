package exorath.cloud.authentication;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * Created by Connor on 12/14/2016.
 */
public class Main {

    public static Service service;
    public static DatabaseProvider databaseProvider;

    public static void main(String[] args) {
//        service = new Service(Integer.parseInt(System.getenv("PORT")));
//        String host = System.getenv("MONGO_HOST");
//        String username = System.getenv("MONGO_USER");
//        String database = System.getenv("MONGO_DATABASE");
//        String password = System.getenv("MONGO_PWD");
        service = new Service(8001);
        String host = "ddkg.flynnhub.com:3162";
        String username = "068076029742ef69838b2eb38b55f772";
        String database = "b4d87ff9bbdfc11609c93ee311a59a2a";
        String password = "58cbe3cffa4a5561f5e9fde56000b61e";
        databaseProvider = new MongoDBProvider(new ServerAddress(host), MongoCredential.createCredential(username, database, password.toCharArray()), database);
    }

}
