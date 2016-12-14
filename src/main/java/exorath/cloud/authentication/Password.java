package exorath.cloud.authentication;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Connor on 12/14/2016.
 */
public class Password {

    public static String generatePasswordHash(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(20));
    }

    public static boolean checkPassowrd(String plaintext, String hash) {
        if (BCrypt.checkpw(plaintext, hash))
            return true;
        else
            return false;
    }

}
