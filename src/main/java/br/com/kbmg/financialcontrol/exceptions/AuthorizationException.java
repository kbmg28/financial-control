package br.com.kbmg.financialcontrol.exceptions;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String msg) {
        super(msg);
    }
}