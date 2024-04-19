package net.devazzarum.chatapp.Services.Contact;

import net.devazzarum.chatapp.Models.Contact;
import net.devazzarum.chatapp.Models.User;
import net.devazzarum.chatapp.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void addContact(User user, User contactUser) {
        // Ajouter l'utilisateur comme contact de l'autre
        contactRepository.save(new Contact(user, contactUser));
        contactRepository.save(new Contact(contactUser, user));
    }

    public List<Contact> getAllContacts(User user) {
        return contactRepository.findByUser(user);
    }

    // Autres méthodes pour supprimer, mettre à jour, etc.
}
