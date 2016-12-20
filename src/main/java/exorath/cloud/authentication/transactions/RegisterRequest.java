package exorath.cloud.authentication.transactions;

import exorath.cloud.authentication.Main;
import exorath.cloud.authentication.data.UserData;
import exorath.cloud.authentication.utils.PasswordHashing;

/**
 * Created by Connor on 12/18/2016.
 */
public class RegisterRequest implements Request {

    private String username;
    private String email;
    private String password;

    @Override
    public Response process() {

        if (Main.databaseProvider.getUserDataByUsername(username) == null) {
            if (Main.databaseProvider.getUserDataByEmail(email) == null) {
                //password validation
                if (PasswordHashing.isAcceptablePassword(password)) {
                    UserData userData = new UserData(username, email, PasswordHashing.generatePasswordHash(password));
                    Main.databaseProvider.addUserData(userData);
                    return new RegisterResponse(200, "Registration complete");
                } else {
                    return new RegisterResponse(400, PasswordHashing.getInvalidReasion(password));
                }
            } else {
                return new RegisterResponse(400, "Email is already used");
            }
        } else {
            return new RegisterResponse(400, "Username is already used");
        }

    }
}
