package exorath.cloud.authentication;

import static spark.Spark.get;

/**
 * Created by Connor on 12/14/2016.
 */
public class Service {

    Service() {
        get("/", (request, response) -> {
            String pass = "test123";
            String hash = Password.generatePasswordHash(pass);
            boolean matches = Password.checkPassowrd(pass, hash);
            return pass + "\n" + hash + "\n" + matches;
        });
    }

}
