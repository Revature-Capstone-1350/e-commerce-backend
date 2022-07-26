package com.revature.util;

public class Regex {
    public static final String SYMBOL = "^.*[@$!%*?&].*$";
    public static final String NUMBER = "^.*[0-9].*$";
    public static final String UPPER = "^.*[A-Z].*$";
    public static final String LOWER = "^.*[a-z].*$";
    public static final String LENGTH = "^[A-Za-z0-9@$!%*?&]{8,}$";
    public static final String ONLY_THESE = "^[A-Za-z0-9@$!%*?&]{0,}$";

    private static final String OR_BLANK = "|(?!.*.{1,})";
    public static final String SYMBOL_OR_BLANK = "^((.*[@$!%*?&].*)"+OR_BLANK+")$";
    public static final String NUMBER_OR_BLANK = "^((.*[0-9].*)"+OR_BLANK+")$";
    public static final String UPPER_OR_BLANK = "^((.*[A-Z].*)"+OR_BLANK+")$";
    public static final String LOWER_OR_BLANK = "^((.*[a-z].*)"+OR_BLANK+")$";
    public static final String LENGTH_OR_BLANK = "^([A-Za-z0-9@$!%*?&]{8,}"+OR_BLANK+")$";
    public static final String ONLY_THESE_OR_BLANK = "^([A-Za-z0-9@$!%*?&]{0,}"+OR_BLANK+")$";
        private Regex() {
            super();
        }
}
