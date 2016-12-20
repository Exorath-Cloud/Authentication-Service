package exorath.cloud.authentication;

import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCursor;
import exorath.cloud.authentication.data.UserData;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 12/20/2016.
 */
public interface DatabaseProvider {

    public UserData getUserDataByUsername(String username);
    public UserData getUserDataByEmail(String email);
    public UserData getUserData(String email, String username);
    public void addUserData(UserData userData);
    public List<UserData> getAllUsers();
    public void updateUserByUsername(String username, UserData userData);
    public void updateUserByEmail(String email, UserData userData);

}
