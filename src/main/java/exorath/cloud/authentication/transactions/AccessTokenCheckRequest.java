package exorath.cloud.authentication.transactions;

import exorath.cloud.authentication.Main;
import exorath.cloud.authentication.data.UserData;

/**
 * Created by Connor on 12/26/2016.
 */
public class AccessTokenCheckRequest implements Request {

  String accesstoken;
  private String ip;

  public AccessTokenCheckRequest(String accesstoken) {
    this.accesstoken = accesstoken;
  }

  @Override
  public AccessTokenCheckResponse process() {
    if (accesstoken == null) {
      return new AccessTokenCheckResponse(null, 400, "Error Parsing");
    }
    UserData userData = Main.databaseProvider.getUserDataByAccessToken(accesstoken, false);
    if (userData != null) {
      return new AccessTokenCheckResponse(userData.getUserid(), 200, "Success");
    } else {
      return new AccessTokenCheckResponse(null, 400, "Authentication failed");
    }

  }

  public void setIP(String ip) {
    this.ip = ip;
  }
}
