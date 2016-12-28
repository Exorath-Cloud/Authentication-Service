package exorath.cloud.authentication;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import exorath.cloud.authentication.data.DatabaseProvider;
import exorath.cloud.authentication.data.UserData;
import exorath.cloud.authentication.utils.Hashing;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.*;

/**
 * Created by Connor on 12/14/2016.
 */
public class MongoDBProvider implements DatabaseProvider {

    final Morphia morphia = new Morphia();
    final Datastore datastore;
    MongoClient mongoClient;

    MongoDBProvider(ServerAddress serverAddress, MongoCredential credential, String database) {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.maxConnectionIdleTime(60000);
        MongoClientOptions opts = builder.build();
        mongoClient = new MongoClient(serverAddress, Arrays.asList(credential),opts);
        morphia.mapPackage("exorath.cloud.authentication");
        datastore = morphia.createDatastore(mongoClient, database);
        datastore.ensureIndexes();
        datastore.delete(datastore.createQuery(UserData.class));
    }

    public UserData getUserDataByUserid(String userid) {
        Query<UserData> query = datastore.createQuery(UserData.class).filter("userid", userid);
        return query.get();
    }

    @Override
    public UserData getUserDataByAccessToken(String accesstoken, boolean hashed) {
        if(!hashed){
            accesstoken = Hashing.sha512(accesstoken);
        }
        System.out.println(accesstoken);
        Query<UserData> query = datastore.createQuery(UserData.class).filter("accessToken.id", accesstoken);
        return query.get();
    }

    public UserData getUserDataByUsername(String username) {
        Query<UserData> query = datastore.createQuery(UserData.class).filter("username", username);
        return query.get();
    }

    public UserData getUserDataByEmail(String email) {
        Query<UserData> query = datastore.createQuery(UserData.class).filter("email", email);
        return query.get();
    }

    public UserData getUserData(String userid, String email, String username) {
        UserData userData = getUserDataByUsername(username);
        if (userData == null) {
            userData = getUserDataByEmail(email);
        }
        if (userData == null) {
            userData = getUserDataByUserid(userid);
        }
        return userData;
    }

    public void saveUserData(UserData userData) {
        datastore.save(userData);
    }

    public List<UserData> getAllUsers() {
        Query<UserData> query = datastore.createQuery(UserData.class);
        return query.asList();
    }

}
