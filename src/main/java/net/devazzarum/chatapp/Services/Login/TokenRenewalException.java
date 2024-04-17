package net.devazzarum.chatapp.Services.Login;

public class TokenRenewalException extends RuntimeException {
    public TokenRenewalException(String message) {
        super(message);
    }
}