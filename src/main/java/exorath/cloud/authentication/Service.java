package exorath.cloud.authentication;

import com.google.gson.GsonBuilder;
import org.bson.Document;

import static spark.Spark.*;


/**
 * Created by Connor on 12/14/2016.
 */
public class Service {

    Service(int port) {
        port(port);
        get("/auth/:username/:password", (request, response) -> {

            System.out.println("--------------------------------------------");
            System.out.println("auth request from " + request.ip());
            String username = request.params(":username");
            String password = request.params(":password");
            System.out.println("Attempting to auth " + username + " " + password);

            UserData userData = Main.databaseProvider.getUserData(username);
            if(userData != null && PasswordHashingUtils.checkHash(password,userData.getPasswordHash())){
                String accessip = request.ip();
                String id = PasswordHashingUtils.randomString(256);
                userData.setAccessToken(new AccessToken(PasswordHashingUtils.generatePasswordHash(id),accessip));
                Main.databaseProvider.updateUser(username,userData);
                response.body(id);
                response.status(200);
            }else{
                response.body("username or password was invalid");
                response.status(400);
            }

            System.out.println(response.body());
            System.out.println("--------------------------------------------");
            return response.body();
        });

        //used for testing remove in live build
        get("/create/:username/:password", (request, response) -> {
            String username = request.params(":username");
            String password = request.params(":password");
            UserData userData = new UserData(username,"email@rmail.com",PasswordHashingUtils.generatePasswordHash(password));
            Main.databaseProvider.addUserData(userData);
            return new GsonBuilder().create().toJson(userData);
        });

        get("/check/:username/:tokenid", (request, response) -> {

            System.out.println("--------------------------------------------");
            System.out.println("token check request from " + request.ip());

            String username = request.params(":username");
            String tokenid = request.params(":tokenid");

            UserData userData = Main.databaseProvider.getUserData(username);
            System.out.println(new GsonBuilder().create().toJson(userData));

            if(userData != null && userData.getAccessToken() != null && userData.getAccessToken().accessip.equalsIgnoreCase(request.ip()) && PasswordHashingUtils.checkHash(tokenid,userData.getAccessToken().getId())){
                response.body("valid");
                response.status(200);
            }else{
                response.body("username or token was invalid");
                response.status(400);
            }

            System.out.println(response.body());
            System.out.println("--------------------------------------------");
            return response.body();
        });

        exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });

    }

}
