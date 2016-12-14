package exorath.cloud.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by Connor on 12/14/2016.
 */
public class Service {

    Logger logger = LoggerFactory.getLogger(getClass());

    Service() {
        port(8000);
        get("/", (request, response) -> {
            String pass = "test123";
            String hash = Password.generatePasswordHash(pass);
            boolean matches = Password.checkPassowrd(pass, hash);
            return pass + "\n" + hash + "\n" + matches;
        });
    }

}
