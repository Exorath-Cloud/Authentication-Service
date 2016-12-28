package exorath.cloud.authentication;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exorath.cloud.authentication.transactions.AccessTokenCheckRequest;
import exorath.cloud.authentication.transactions.AccessTokenCheckResponse;
import exorath.cloud.authentication.transactions.AuhResponse;
import exorath.cloud.authentication.transactions.AuthRequest;
import exorath.cloud.authentication.transactions.RegisterRequest;
import exorath.cloud.authentication.transactions.RegisterResponse;


/**
 * Created by Connor on 12/14/2016.
 */
public class Service {

  Service(int port) {
    port(port);
    post("/auth", (request, response) -> {
      Gson gson = new GsonBuilder().create();
      AuthRequest authRequest = gson.fromJson(request.body(), AuthRequest.class);
      if (authRequest != null) {
        authRequest.setIP(request.ip());
        AuhResponse auhResponse = authRequest.process();
        response.status(auhResponse.getStatus());
        return auhResponse.getBody();
      } else {
        response.status(400);
        return "error invalid json";
      }
    });

    post("/auth/register", (request, response) -> {
      Gson gson = new GsonBuilder().create();
      RegisterRequest registerRequest = gson.fromJson(request.body(), RegisterRequest.class);
      RegisterResponse auhResponse = registerRequest.process();
      response.status(auhResponse.getStatus());
      return auhResponse.getBody();
    });

    get("/auth/:accesstoken", (request, response) -> {
      AccessTokenCheckRequest accessTokenCheckRequest = new AccessTokenCheckRequest(
          request.params(":accesstoken"));
      accessTokenCheckRequest.setIP(request.ip());
      AccessTokenCheckResponse accessTokenCheckResponse = accessTokenCheckRequest.process();
      response.status(accessTokenCheckResponse.getStatus());
      return accessTokenCheckResponse.getBody();
    });

    exception(Exception.class, (exception, request, response) -> {
      exception.printStackTrace();
    });

  }

}
