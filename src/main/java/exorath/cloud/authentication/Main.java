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
//        service = new Service(Integer.parseInt(System.getenv("PORT")));
        service = new Service(8000);
//        mongo --username=0f1c6e560223f5e4011cc82335be57e7 --password=f72622a025bccdb98ccea99f7f0f7489 --host=zkj2.flynnhub.com --port=3190 7d8683dcfb828c87e85b3398f7d34673
        String host = "zkj2.flynnhub.com:3190";
//        String host = System.getenv("MONGO_HOST");
        String username = "0f1c6e560223f5e4011cc82335be57e7";
//        String username = System.getenv("MONGO_USER");
        String database = "7d8683dcfb828c87e85b3398f7d34673";
//        String database = System.getenv("MONGO_DATABASE");
        String password = "f72622a025bccdb98ccea99f7f0f7489";
//        String password = System.getenv("MONGO_PWD");
        databaseProvider = new DatabaseProvider(new ServerAddress(host),MongoCredential.createCredential(username, database, password.toCharArray()),database);
//        databaseProvider = new DatabaseProvider(MongoCredential.createCredential(System.getenv("MONGO_USER"), System.getenv("MONGO_DATABASE"), System.getenv("MONGO_PWD").toCharArray()),System.getenv("MONGO_DATABASE"));
    }

}
