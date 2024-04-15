package net.devazzarum.chatapp.Services;

import net.devazzarum.chatapp.Models.User;
import net.devazzarum.chatapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {

        return userRepository.save(user);
    }


}
