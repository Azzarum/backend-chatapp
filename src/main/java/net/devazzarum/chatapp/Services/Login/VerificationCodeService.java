package net.devazzarum.chatapp.Services.Login;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class VerificationCodeService {

    // Méthode pour générer un code de vérification aléatoire
    public String generateVerificationCode() {
        // Générer un code aléatoire de 6 chiffres
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // Méthode pour vérifier si le code est encore valide (par exemple, s'il est expiré)
    public boolean isCodeValid(String code) {
        // Implémentez la logique pour vérifier si le code est encore valide
        // Vous pouvez comparer le code avec une date d'expiration ou utiliser d'autres mécanismes de validation
        return true; // Par défaut, on suppose que le code est toujours valide
    }
}
