package exorath.cloud.authentication;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import exorath.cloud.authentication.data.DatabaseProvider;
import exorath.cloud.authentication.data.UserData;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Connor on 12/14/2016.
 */
public class MongoDBProvider implements DatabaseProvider {

    final Morphia morphia = new Morphia();
    final Datastore datastore;
    MongoClient mongoClient;

    MongoDBProvider(ServerAddress serverAddress, MongoCredential credential, String database) {
        mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        morphia.mapPackage("exorath.cloud.authentication");
        datastore = morphia.createDatastore(new MongoClient(), database);
        datastore.ensureIndexes();
    }

    public UserData getUserDataByUserid(String userid) {
        Query<UserData> query = datastore.createQuery(UserData.class).filter("userid", userid);
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
        ArrayList<UserData> userDatas = new ArrayList<>();
        Query<UserData> query = datastore.createQuery(UserData.class);
        for (UserData aQuery : query) {
            userDatas.add(aQuery);
        }
        return userDatas;
    }

}
