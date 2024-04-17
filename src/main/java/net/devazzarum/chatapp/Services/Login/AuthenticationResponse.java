package net.devazzarum.chatapp.Services.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String refreshToken;
    private String username;
    private String prenom;
    private String phone;
    private int id;
    private String email;
    private String nom;
    private Boolean isAuthenticated;
}
