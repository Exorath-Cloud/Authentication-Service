package exorath.cloud.authentication.transactions;

import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Created by Connor on 12/17/2016.
 */
public class AuhResponse implements Response{

    String tokenid;
    Date expiry;
    int status;
    String errorMessage;

    AuhResponse(String tokenid,Date expiry, int status,String errorMessage){
        this.tokenid = tokenid;
        this.expiry = expiry;
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
