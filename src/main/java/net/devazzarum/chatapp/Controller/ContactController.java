package net.devazzarum.chatapp.Controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.EntityNotFoundException;
import net.devazzarum.chatapp.Models.AddContactRequest;
import net.devazzarum.chatapp.Models.Contact;
import net.devazzarum.chatapp.Models.User;
import net.devazzarum.chatapp.Repository.UserRepository;
import net.devazzarum.chatapp.Services.Contact.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contact")
public class ContactController {

    private final UserRepository userRepository;
    private final ContactService contactService;

    public ContactController(UserRepository userRepository, ContactService contactService) {
        this.userRepository = userRepository;
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllContacts() {
        // Récupérer le nom d'utilisateur à partir du jeton JWT
        String username = extractUsernameFromJWT();

        // Rechercher l'utilisateur dans la base de données
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable avec le nom d'utilisateur: " + username));

        // Récupérer tous les contacts de l'utilisateur
        List<Contact> contacts = contactService.getAllContacts(currentUser);

        // Retourner les contacts en réponse à la requête
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }


    private String extractUsernameFromJWT() {
        // Récupérer l'objet d'authentification de Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifier si l'objet d'authentification est valide et si le principal est une chaîne (nom d'utilisateur)
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            // Retourner le nom d'utilisateur extrait du principal de l'authentification
            return (String) authentication.getPrincipal();
        } else {
            // Si l'authentification est invalide ou si le principal n'est pas une chaîne, renvoyer null ou une valeur par défaut
            return null;
        }
    }



}
