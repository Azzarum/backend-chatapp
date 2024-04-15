package net.devazzarum.chatapp.Controller;

import net.devazzarum.chatapp.Models.User;
import net.devazzarum.chatapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/createuser")
    public ResponseEntity<User> CreateUser(@RequestBody User user) {
        User CreateUser = userService.createUser(user);
        return new ResponseEntity<>(CreateUser, HttpStatus.CREATED);
    }
}
