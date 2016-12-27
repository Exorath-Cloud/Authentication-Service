package exorath.cloud.authentication.data;

import com.google.gson.GsonBuilder;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Map;

/**
 * Created by Connor on 12/14/2016.
 */

@Entity("user")
public class UserData {

    @Id
    private String userid;
    private String username; //the unique identifier of the user
    private String email; //email address of the user (will also be unique)
    private String passwordHash; //the bcrypt hashed password with salt

    private boolean verified; //whether or not this user has verified his email address
    private boolean enabled; //whether or not this account is enabled, an account can be disabled when it's banned

    @Embedded
    private AccessToken accessToken; //session id for browsers, will regenerate after a certain timespan

    @Embedded
    private Map<String, ResetToken> resetTokensById; //The password reset tokens by their id, they expire after a timespan and are single use.

    public UserData(String userid, String username, String email, String passwordHash) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public UserData(){}

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}
