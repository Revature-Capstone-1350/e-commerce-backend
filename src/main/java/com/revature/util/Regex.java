package com.revature.util;

public class Regex {
    public static final String SYMBOL = "^.*[@$!%*?&].*$";
    public static final String NUMBER = "^.*[0-9].*$";
    public static final String UPPER = "^.*[A-Z].*$";
    public static final String LOWER = "^.*[a-z].*$";
    public static final String LENGTH = "^[A-Za-z0-9@$!%*?&]{8,}$";
    public static final String ONLY_THESE = "^[A-Za-z0-9@$!%*?&]{0,}$";
        private Regex() {
            super();
        }
}
