package net.devazzarum.chatapp.Services.Login;


public class VerificationRequest {

    private String code;
    private String emailOrUsername;

    public VerificationRequest() {
    }

    public VerificationRequest(String code, String emailOrUsername) {
        this.code = code;
        this.emailOrUsername = emailOrUsername;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }
}