package exorath.cloud.authentication.data;

import java.util.Map;

/**
 * Created by Connor on 12/14/2016.
 */
public class UserData {

    private String username; //the unique identifier of the user
    private String email; //email address of the user (will also be unique)
    private String passwordHash; //the bcrypt hashed password with salt

    private boolean verified; //whether or not this user has verified his email address
    private boolean enabled; //whether or not this account is enabled, an account can be disabled when it's banned

    private AccessToken accessToken; //session id for browsers, will regenerate after a certain timespan

    private Map<String, ResetToken> resetTokensById; //The password reset tokens by their id, they expire after a timespan and are single use.

    public UserData(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

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

}
