package com.example.deliverybox.Helpers;

public class StringHelper {
    public static boolean regexValidationPattern(String string){
        String regexEmail = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,126}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+");

        String regexPass = ("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                //(?=.*[a-zA-Z])" +
                //"(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{6,}" +
                "$");

        String regexPhone = ("[0-9]{10,13}");

        if(string.matches(regexEmail)){
            return true;
        }

        if (string.matches(regexPass)) {
            return true;
        }

        if (string.matches(regexPhone)) {
            return true;
        }

        return false;
    }
}
