/*
 * Copyright 2016 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package exorath.cloud.authentication.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;

/**
 * Created by Connor on 12/14/2016.
 */
public class PasswordHashing {

    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 20;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String generatePasswordHash(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(12));
    }

    public static boolean checkHash(String plaintext, String hash) {
        if (BCrypt.checkpw(plaintext, hash))
            return true;
        else
            return false;
    }

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static boolean isAcceptablePassword(String password) {
        if (password.isEmpty()) {
            return false;
        }
        password = password.trim();
        int len = password.length();
        if (len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
            return false;
        }
        char[] aC = password.toCharArray();
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        boolean specChar = false;
        boolean validChars = true;
        for (char c : aC) {
            if (Character.isUpperCase(c)) {
                upperCase = true;
            } else if (Character.isLowerCase(c)) {
                lowerCase = true;
            } else if (Character.isDigit(c)) {
                digit = true;
            } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                specChar = true;
            } else {
                validChars = false;
            }
        }
        return upperCase && lowerCase && digit && specChar && validChars;
    }

    public static String getInvalidReasion(String password) {
        if (password.isEmpty()) {
            return "Password is empty";
        }
        password = password.trim();
        int len = password.length();
        if (len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
            return "Password must be 8-20 characters in length";
        }
        char[] aC = password.toCharArray();
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        boolean specChar = false;
        boolean validChars = true;
        for (char c : aC) {
            if (Character.isUpperCase(c)) {
                upperCase = true;
            } else if (Character.isLowerCase(c)) {
                lowerCase = true;
            } else if (Character.isDigit(c)) {
                digit = true;
            } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                specChar = true;
            } else {
                validChars = false;
            }
        }

        if (!upperCase) {
            return "Password must contain a upper case character";
        } else if (!lowerCase) {
            return "Password must contain a lower case character";
        } else if (!digit) {
            return "Password must contain a digit";
        } else if (!specChar) {
            return "Password must contain a special character !@#$%^&*()~`-=_+[]{}|:\";',./<>?";
        } else if (!validChars) {
            return "Password must only contain characters, digits and  special characters";
        }

        return "Unknown";

    }

}
