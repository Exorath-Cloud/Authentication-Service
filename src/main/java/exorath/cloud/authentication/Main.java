package exorath.cloud.authentication;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * Created by Connor on 12/14/2016.
 */
public class Main {

    static Service service;
    static DatabaseProvider databaseProvider;

    public static void main(String[] args) {
        service = new Service(Integer.parseInt(System.getenv("PORT")));
        String host = System.getenv("MONGO_HOST");
        String username = System.getenv("MONGO_USER");
        String database = System.getenv("MONGO_DATABASE");
        String password = System.getenv("MONGO_PWD");
        databaseProvider = new DatabaseProvider(new ServerAddress(host),MongoCredential.createCredential(System.getenv("MONGO_USER"), System.getenv("MONGO_DATABASE"), System.getenv("MONGO_PWD").toCharArray()),System.getenv("MONGO_DATABASE"));
//        service = new Service(8000);
//        String host = "zkj2.flynnhub.com:3190";
//        String username = "0f1c6e560223f5e4011cc82335be57e7";
//        String database = "7d8683dcfb828c87e85b3398f7d34673";
//        String password = "f72622a025bccdb98ccea99f7f0f7489";
//        databaseProvider = new DatabaseProvider(new ServerAddress(host),MongoCredential.createCredential(username, database, password.toCharArray()),database);
    }

}
