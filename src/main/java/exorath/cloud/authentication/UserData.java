package exorath.cloud.authentication;

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
    
}
