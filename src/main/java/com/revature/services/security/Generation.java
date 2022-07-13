package com.revature.services.security;

public class Generation {
    public static String generatePassword(String password) {
        byte[] bytes = password.getBytes();
        String s = new String(bytes);
        return s;
    }
}
