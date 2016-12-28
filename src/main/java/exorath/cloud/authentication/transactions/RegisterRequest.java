package exorath.cloud.authentication.transactions;

import exorath.cloud.authentication.Main;
import exorath.cloud.authentication.data.UserData;
import exorath.cloud.authentication.utils.Hashing;
import java.util.UUID;

/**
 * Created by Connor on 12/18/2016.
 */
public class RegisterRequest implements Request {

  private String username;
  private String email;
  private String password;

  @Override
  public RegisterResponse process() {
    if (username == null || email == null || password == null) {
      return new RegisterResponse(400, "Parsing Error");
    }
    if (Main.databaseProvider.getUserDataByUsername(username) == null) {
      if (Main.databaseProvider.getUserDataByEmail(email) == null) {
        if (Hashing.validateEmail(email)) {
          if (Hashing.isAcceptablePassword(password)) {
            UserData userData = new UserData(UUID.randomUUID().toString(), username, email,
                Hashing.generatePasswordHash(password));
            Main.databaseProvider.saveUserData(userData);
            return new RegisterResponse(200, "Registration complete");
          } else {
            return new RegisterResponse(400, Hashing.getInvalidReasion(password));
          }
        } else {
          return new RegisterResponse(400, "Invalid email");
        }

      } else {
        return new RegisterResponse(400, "Email is already used");
      }
    } else {
      return new RegisterResponse(400, "Username is already used");
    }

  }

}
