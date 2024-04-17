package net.devazzarum.chatapp.Services.Login;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioService {

    private final String ACCOUNT_SID;
    private final String AUTH_TOKEN;

    public TwilioService(String accountSid, String authToken) {
        this.ACCOUNT_SID = accountSid;
        this.AUTH_TOKEN = authToken;
    }

    public void sendVerificationCode(String phoneNumber, String verificationCode) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Envoyer un message en utilisant l'API Twilio
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber("votre_numero_twilio"),
                        "Votre code de vérification est : " + verificationCode)
                .create();

        // Afficher l'identifiant du message dans la console (facultatif)
        System.out.println("Message SID : " + message.getSid());
    }
}
