package exorath.cloud.authentication.transactions;

import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Created by Connor on 12/18/2016.
 */
public class RegisterResponse implements Response{

    int status;
    String errorMessage;

    RegisterResponse(int status,String errorMessage){
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
