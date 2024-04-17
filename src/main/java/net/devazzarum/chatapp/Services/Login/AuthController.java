package net.devazzarum.chatapp.Services.Login;

import lombok.RequiredArgsConstructor;
import net.devazzarum.chatapp.Services.Login.AuthenticationResponse;
import net.devazzarum.chatapp.Services.Login.AuthenticationService;
import net.devazzarum.chatapp.Services.Login.AuthentificationRequest;
import net.devazzarum.chatapp.Services.Login.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginAndAuthentification")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthentificationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
