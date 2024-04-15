package net.devazzarum.chatapp.Services.Login;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.devazzarum.chatapp.Models.User;

public class CustomUserDeserializer extends StdDeserializer<User> {

    public CustomUserDeserializer() {
        this(null);
    }

    public CustomUserDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int id = node.has("id") ? node.get("id").asInt() : 0;
        String nom = node.has("nom") ? node.get("nom").asText() : null;
        String prenom = node.has("prenom") ? node.get("prenom").asText() : null;
        String email = node.has("email") ? node.get("email").asText() : null;
        String newPassword = node.has("newPassword") ? node.get("newPassword").asText() : null;
        String username = node.has("username") ? node.get("username").asText() : null;
        String phone = node.has("phone") ? node.get("phone").asText() : null;
        Timestamp created_at = node.has("created_at") ? Timestamp.valueOf(node.get("created_at").asText()) : null;

        User user = new User();
        user.setId(id);
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setEmail(email);
        user.setUsername(username);
        user.setPhone(phone);
        user.setCreated_at(created_at);

        if (newPassword != null) {
            user.setPassword(newPassword);
        }

        return user;
    }


}
