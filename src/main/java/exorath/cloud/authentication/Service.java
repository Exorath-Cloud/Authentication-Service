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

            System.out.println("Received auth request from " + request.ip());

            String username = request.params(":username");
            String password = request.params(":password");

            UserData userData = Main.databaseProvider.getUserData(username);

            if(PasswordHashingUtils.checkHash(password,userData.getPasswordHash())){
                String accessip = request.ip();
                String id = PasswordHashingUtils.randomString(256);
                userData.setAccessToken(new AccessToken(PasswordHashingUtils.generatePasswordHash(id),accessip));
                response.status(200);
                response.body(id);
            }else{
                response.body("username or password was invalid");
                response.status(400);
            }

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

    }

}
