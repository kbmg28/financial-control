package br.com.kbmg.financialcontrol.util;

public abstract class KeyMessageConstants {

    private KeyMessageConstants() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static final String USER_OR_PASSWORD_INCORRECT = "user.or.password.incorrect";
    public static final String USER_NOT_FOUND = "user.not.found";

}