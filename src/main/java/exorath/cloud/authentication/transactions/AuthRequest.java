package exorath.cloud.authentication.transactions;

import exorath.cloud.authentication.Main;
import exorath.cloud.authentication.data.AccessToken;
import exorath.cloud.authentication.data.UserData;
import exorath.cloud.authentication.utils.Hashing;

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
    UserData userData = Main.databaseProvider.getUserData(userid, email, username);
    if (userData != null) {
      if (Hashing.checkHash(password, userData.getPasswordHash())) {
        String accessTokenId = Hashing.randomString(AccessToken.ID_LENGTH);
        String hashedAccessTokenId = Hashing.sha512(accessTokenId);
        AccessToken accessToken = new AccessToken(hashedAccessTokenId, ip);
        userData.setAccessToken(accessToken);
        Main.databaseProvider.saveUserData(userData);
        return new AuhResponse(accessTokenId, accessToken.getExpiry(), 200, "Success");
      } else {
        return new AuhResponse(null, null, 400, "Invalid password");
      }
    } else {
      return new AuhResponse(null, null, 400, "Invalid username");
    }
  }

  public void setIP(String ip) {
    this.ip = ip;
  }
}
