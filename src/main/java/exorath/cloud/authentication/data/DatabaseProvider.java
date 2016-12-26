package exorath.cloud.authentication.data;

import java.util.List;

/**
 * Created by Connor on 12/20/2016.
 */
public interface DatabaseProvider {

    UserData getUserDataByUsername(String username);

    UserData getUserDataByEmail(String email);

    UserData getUserData(String userid, String email, String username);

    void saveUserData(UserData userData);

    List<UserData> getAllUsers();

    UserData getUserDataByUserid(String userid);

}
