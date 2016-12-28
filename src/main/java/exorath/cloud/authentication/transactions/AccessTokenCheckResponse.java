package exorath.cloud.authentication.transactions;

import com.google.gson.GsonBuilder;

/**
 * Created by Connor on 12/26/2016.
 */
public class AccessTokenCheckResponse implements Response {

  String userid;
  int status;
  String errorMessage;

  AccessTokenCheckResponse(String userid, int status, String errorMessage) {
    this.userid = userid;
    this.status = status;
    this.errorMessage = errorMessage;
  }


  @Override
  public String getBody() {
    return new GsonBuilder().create().toJson(this);
  }

  @Override
  public int getStatus() {
    return status;
  }
}
