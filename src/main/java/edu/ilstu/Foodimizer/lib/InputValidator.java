package edu.ilstu.Foodimizer.lib;

public class InputValidator {
    public InputValidator() {

    }
    public static boolean validateProfileName(String profileName) {
        String pattern = "^[a-zA-Z0-9 .,']{1,100}$";
        return profileName.matches(pattern);
    }
}
