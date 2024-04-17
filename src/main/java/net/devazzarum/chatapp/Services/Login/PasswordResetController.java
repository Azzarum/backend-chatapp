package net.devazzarum.chatapp.Services.Login;

import net.devazzarum.chatapp.Models.User;
import net.devazzarum.chatapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController

public class PasswordResetController {
    @Autowired
    public PasswordResetController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }
    private final TwilioService twilioService;

    private Map<String, VerificationCodeEntry> verificationCodes = new HashMap<>();

    @Autowired
    private UserService userService;



    private static final int VERIFICATION_CODE_EXPIRATION_MINUTES = 10;

    @PostMapping("/reset-password/request")
    public ResponseEntity<?> requestPasswordReset(@RequestBody String emailOrUsername) {
        // Vérifier si l'utilisateur existe dans la base de données
        User user = userService.findUserByEmailOrUsername(emailOrUsername);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Générer un code de vérification avec horodatage d'expiration
        VerificationCodeEntry verificationCodeEntry = generateVerificationCode();
        verificationCodes.put(user.getEmail(), verificationCodeEntry);

        // Afficher le contenu de la Map après avoir ajouté le code de vérification
        System.out.println("Verification codes map after adding new entry:");
        for (Map.Entry<String, VerificationCodeEntry> entry : verificationCodes.entrySet()) {
            System.out.println("Email: " + entry.getKey() + ", Verification Code: " + entry.getValue().getCode());
        }

        // Envoyer le code de vérification par SMS (à implémenter)

        return ResponseEntity.ok("Verification code sent successfully");
    }


    @PostMapping("/reset-password/verify")
    public ResponseEntity<?> verifyVerificationCode(@RequestBody VerificationRequest verificationRequest) {
        String code = verificationRequest.getCode();
        String emailOrUsername = verificationRequest.getEmailOrUsername();

        // Récupérer le code de vérification associé à l'adresse e-mail de l'utilisateur depuis la Map
        VerificationCodeEntry storedVerificationCodeEntry = verificationCodes.get(emailOrUsername);
        if (storedVerificationCodeEntry == null || !storedVerificationCodeEntry.getCode().equals(code)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired verification code");
        }

        // Vérifier si le code de vérification est encore valide
        if (System.currentTimeMillis() > storedVerificationCodeEntry.getExpirationTime()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification code expired");
        }

        // Supprimer le code de vérification de la Map après utilisation
        verificationCodes.remove(emailOrUsername);

        // Réinitialiser le mot de passe de l'utilisateur (à implémenter)

        return ResponseEntity.ok("Password reset successfully");
    }

    private VerificationCodeEntry generateVerificationCode() {
        String code = UUID.randomUUID().toString().substring(0, 6);
        long expirationTime = System.currentTimeMillis() + (VERIFICATION_CODE_EXPIRATION_MINUTES * 60 * 1000);

        return new VerificationCodeEntry(code, expirationTime);
    }

    private static class VerificationCodeEntry {
        private String code;
        private long expirationTime;

        public VerificationCodeEntry(String code, long expirationTime) {
            this.code = code;
            this.expirationTime = expirationTime;
        }

        public String getCode() {
            return code;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }
}
