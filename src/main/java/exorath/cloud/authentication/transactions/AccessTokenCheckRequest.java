package exorath.cloud.authentication.transactions;

import exorath.cloud.authentication.Main;
import exorath.cloud.authentication.data.UserData;
import exorath.cloud.authentication.utils.PasswordHashing;

/**
 * Created by Connor on 12/26/2016.
 */
public class AccessTokenCheckRequest implements Request{

    String accesstoken;
    private String userid;
    private String username;
    private String email;
    private String password;
    private String ip;

    @Override
    public AccessTokenCheckResponse process() {
        if(accesstoken == null || (userid == null && email == null && username == null) || ip == null){
            return new AccessTokenCheckResponse(400,"Error Parsing");
        }
        UserData userData = Main.databaseProvider.getUserData(userid,email,username);
        if (userData != null){
            if(userData.getAccessToken().getIP().equalsIgnoreCase(ip) && PasswordHashing.checkHash(accesstoken,userData.getAccessToken().getId())){
                return new AccessTokenCheckResponse(200,"Success");
            }else {
                return new AccessTokenCheckResponse(400,"Authentication failed");
            }
        }else{
            return new AccessTokenCheckResponse(400,"User not found");
        }

    }

    public void setIP(String ip) {
        this.ip = ip;
    }
}
