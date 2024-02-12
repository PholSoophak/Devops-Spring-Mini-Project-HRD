package com.pvh.group3_spring_mini_project001.model.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    /*    (?=.*[0-9]) a digit must occur at least once
                (?=.*[a-z]) a lower case letter must occur at least once
                (?=.*[A-Z]) an upper case letter must occur at least once
                (?=.*[@#$%^&+=]) a special character must occur at least once
                (?=\\S+$) no whitespace allowed in the entire string
                .{8,} at least 8 characters
     */
    public static boolean validatePassword(String password) {
        String regExpn =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        CharSequence inputStr = password;
        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            return true;
        }else {
            return false;
        }
    }
    //email
    public static boolean validateEmail(String email){
//        String regExpan = "@\"^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$\"";
//        CharSequence inputEmail = email;
//        Pattern pattern = Pattern.compile(regExpan,Pattern.CASE_INSENSITIVE);
//        Matcher matcher=pattern.matcher(inputEmail);
        final Pattern EMAIL_REGEX = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}",
                Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

}
