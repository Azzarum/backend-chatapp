package net.devazzarum.chatapp.Models;

public class AddContactRequest {
    private String userEmail;
    private String contactIdentifier; // Renommez l'attribut pour correspondre à la méthode dans le contrôleur

    public AddContactRequest(String userEmail, String contactIdentifier) {
        this.userEmail = userEmail;
        this.contactIdentifier = contactIdentifier;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getContactIdentifier() {
        return contactIdentifier;
    }

    public void setContactIdentifier(String contactIdentifier) {
        this.contactIdentifier = contactIdentifier;
    }
}
