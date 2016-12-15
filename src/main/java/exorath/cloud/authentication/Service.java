package exorath.cloud.authentication;

import com.google.gson.GsonBuilder;
import org.bson.Document;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by Connor on 12/14/2016.
 */
public class Service {

    Service() {
        port(Integer.parseInt(System.getenv("PORT")));
        get("/auth/:username/:password/", (request, response) -> {

            String username = request.params(":username");
            String password = request.params(":password");

            UserData userData = new UserData(username,"123@123.com",password);
            Document document = Document.parse(new GsonBuilder().create().toJson(userData));
            UserData userData1 = new GsonBuilder().create().fromJson(document.toJson(),UserData.class);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(new GsonBuilder().create().toJson(userData));
            stringBuilder.append("<br>");
            stringBuilder.append(new GsonBuilder().create().toJson(userData1));
            stringBuilder.append("<br>");
            response.body(stringBuilder.toString());

            return response.body();

        });
    }

}
