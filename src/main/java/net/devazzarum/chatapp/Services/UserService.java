package net.devazzarum.chatapp.Services;

import net.devazzarum.chatapp.Models.User;
import net.devazzarum.chatapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {

        return userRepository.save(user);
    }
    public User findUserByEmailOrUsername(String emailOrUsername) {
        // Recherchez l'utilisateur par son email
        Optional<User> userByEmail = userRepository.findByEmail(emailOrUsername);
        if (userByEmail.isPresent()) {
            return userByEmail.get();
        }

        // Si l'utilisateur n'est pas trouvé par email, recherchez par nom d'utilisateur
        return userRepository.findByUsername(emailOrUsername).orElse(null);
    }
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }




}
