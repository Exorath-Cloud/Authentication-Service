package exorath.cloud.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by Connor on 12/14/2016.
 */
public class Service {

    Service() {
        port(8000);
        get("/auth/:username/:password", (request, response) -> {
            String user = request.params(":username");
            String pasword = request.params(":password");
            //replace test hash with hash from db
            String testhash = PasswordHashingUtils.generatePasswordHash("test123");
           if(PasswordHashingUtils.checkHash(pasword, testhash)){
               response.status(200);
               String id = PasswordHashingUtils.randomString(1024);
               String accessip = request.ip();
               AccessToken accessToken = new AccessToken(PasswordHashingUtils.generatePasswordHash(id),accessip);
               //get usersdata and add accesstoekn
               response.body(id);
           }else {
               response.status(403);
               response.body("Your username or password was invalid");
           }
           return response;
        });
    }

}
