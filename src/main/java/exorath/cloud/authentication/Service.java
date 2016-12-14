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
        get("/auth/:username/:password", (request, response) -> {
            String user = request.params(":username");
            String pasword = request.params(":password");
            String testhash = Password.generatePasswordHash("test123");
           if(Password.checkPassowrd(pasword, testhash)){
               return "Authed";
           }else {
               return "failed";
           }
        });
    }

}
