package com.revature.util;

public class ValidatorMessageUtil {
    /* OnCreate User Validation Error Messages */
    public static final String EMAIL_REQUIRED_ON_CREATE = "Email field is expected but wasn't provided";
    public static final String FIRST_NAME_REQUIRED_ON_CREATE = "First name is required";
    public static final String FNAME_REQUIRED_ON_CREATE = "A first name must be provided to register for an account";
    public static final String LNAME_REQUIRED_ON_CREATE = "A last name must be provided to register for an account";
    public static final String PASSWORD_REQUIRED_ON_CREATE = "You must provide a password to create and login to your account.";

    /* General User Validation Error Messages */
    public static final String EMAIL_REQUIRED = "A email must be provided.  Example: john_doe@example.com";
    public static final String EMAIL_REQUIREMENTS = "A valid email must be provided";
    public static final String PASSWORD_REQUIRED = "You must provide a password.";
    public static final String PASSWORD_REQUIREMENTS = "Passwords must be eight characters long and contain at least one of the following: lowercase letter, uppercase letter, number, special character";

}
