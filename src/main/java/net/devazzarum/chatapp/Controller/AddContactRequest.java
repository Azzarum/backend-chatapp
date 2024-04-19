package net.devazzarum.chatapp.Controller;

public class AddContactRequest {
    private String userEmail;
    private String contactEmail;

    public AddContactRequest(String userEmail, String contactEmail) {
        this.userEmail = userEmail;
        this.contactEmail = contactEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    // Méthode pour obtenir l'identifiant du contact (peut être l'email ou un autre identifiant)
    public String getContactIdentifier() {
        return contactEmail; // Par exemple, nous utilisons l'email comme identifiant de contact
    }
}
