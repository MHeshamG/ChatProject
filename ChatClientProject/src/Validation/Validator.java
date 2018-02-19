/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mohamed hesham
 */
public class Validator {

    public static boolean isValidEmail(String email) {

        if (email != null && email.length() <= 40) {
            Pattern p = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
            Matcher m = p.matcher(email);
            return m.find();
        }
        return false;
    }

    public static boolean isValidName(String name) {

        if (name != null && name.length() <= 40) {
            Pattern p = Pattern.compile("[a-zA-Z]*");
            Matcher m = p.matcher(name);
            return m.find();
        }
        return false;
    }

    public static boolean isValidUserName(String userName) {
        if (userName != null && userName.length() <= 40) {

            Pattern p = Pattern.compile("[a-zA-Z0-9]*");
            Matcher m = p.matcher(userName);
            return m.find();
        }
        return false;

    }

    public static boolean isValidPassword(String password) {
        if (password != null && password.length() <= 40 && !password.equals("")) {
            return true;
        }
        return false;

    }

}
