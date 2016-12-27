package exorath.cloud.authentication.transactions;

import com.google.gson.GsonBuilder;
import exorath.cloud.authentication.Main;
import exorath.cloud.authentication.data.AccessToken;
import exorath.cloud.authentication.data.UserData;
import exorath.cloud.authentication.utils.PasswordHashing;

/**
 * Created by Connor on 12/17/2016.
 */
public class AuthRequest implements Request {

    private String userid;
    private String username;
    private String email;
    private String password;
    private String ip;

    @Override
    public AuhResponse process() {
        UserData userData = Main.databaseProvider.getUserData(userid,email,username);
        if (userData != null) {
            if (PasswordHashing.checkHash(password, userData.getPasswordHash())) {
                String tokenid = PasswordHashing.randomString(AccessToken.ID_LENGTH);
                AccessToken accessToken = new AccessToken(tokenid, ip);
                userData.setAccessToken(accessToken);
                Main.databaseProvider.saveUserData(userData);
                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(Main.databaseProvider.getAllUsers()));
                return new AuhResponse(tokenid, accessToken.getExpiry(), 200, "Success");
            }else{
                return new AuhResponse(null, null, 400, "Invalid password");
            }
        }else{
            return new AuhResponse(null, null, 400, "Invalid username");
        }
    }

    public void setIP(String ip) {
        this.ip = ip;
    }
}
