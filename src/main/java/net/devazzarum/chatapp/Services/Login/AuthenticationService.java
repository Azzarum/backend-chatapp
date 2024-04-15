package net.devazzarum.chatapp.Services.Login;


import lombok.RequiredArgsConstructor;
import net.devazzarum.chatapp.Models.User;
import net.devazzarum.chatapp.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String createdAtString = String.valueOf(request.getCreated_at());
        Timestamp createdAt = Timestamp.valueOf(createdAtString);
        String password = request.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        String username = request.getUsername();

        var user = User.builder()
                .prenom(firstName)
                .nom(lastName)
                .email(request.getEmail())
                .phone(request.getPhone())
                .created_at(createdAt)
                .username(username)
                .password(hashedPassword)
                .build();

        userRepository.save(user);
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(JwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthentificationRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        var JwtToken = jwtService.generateToken(user);
        String refreshToken = JwtService.createRefreshToken(user.getEmail());

        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .nom(user.getNom())
                .isAuthenticated(true)
                .token(JwtToken)
                .refreshToken(refreshToken)
                .build();
    }



    }

