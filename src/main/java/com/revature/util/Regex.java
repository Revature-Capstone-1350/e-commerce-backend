package com.revature.util;

public class Regex {
    public static final String PASSWORD =
                      "^"                    // start of regex
                    + "(?=.*[a-z])"         // at least 1 lowercase
                    + "(?=.*[A-Z])"         // at least 1 uppercase
                    + "(?=.*\\d)"           // at least 1 digit
                    + "(?=.*[@$!%*?&])"     // at least 1 special character
                    + "[A-Za-z\\d@$!%*?&]"  // can only contain these characters
                    + "{8,}"                // must be at least 8 characters
                    + "$";                  // end of regex

    private Regex() {
        super();
    }
}
